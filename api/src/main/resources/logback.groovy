def daemon = 'cms' // service name
/**
 * Set default logging level
 * http://logback.qos.ch/apidocs/ch/qos/logback/classic/Level.html#toLevel%28string%29
 */
def level = toLevel(System.getenv('LOGGING_LEVEL'), WARN)
def isSyslogEnabled = (System.getenv('LOGGING_SYSLOG')?:'false').equalsIgnoreCase('true')
def sysLoggerHost = System.getenv('LOGGING_HOST')?:'edibox'
def appenders = []

def patterns = [
        file:   '%date{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %level %logger:%line - %message%n',
        syslog: daemon + ': [%thread] %level %logger:%line - %message%n',
        visual: '%date{yyyy-MM-dd HH:mm:ss.SSS} %gray([%thread]) %highlight(%level) %cyan(%logger{32}) : %green(%line) - %message%n'
]

if (isSyslogEnabled) {
    appender('SYSLOG', SyslogAppender) {
        syslogHost = sysLoggerHost //'thoth.redlounge.io'
        facility = 'daemon'
        suffixPattern = patterns.syslog
    }
    appenders += 'SYSLOG'
}
else {
    appender('STDOUT', ConsoleAppender) {
        encoder(PatternLayoutEncoder) {
            pattern = patterns.visual
        }
    }
    appenders += 'STDOUT'
}


/* we do not use file logging by default, see SYSLOG appender above
appender('FILE', FileAppender) {
    file = dir + File.separator + daemon + '.log'
    encoder(PatternLayoutEncoder) {
        pattern = patterns.file
    }
}
*/


logger('com.rainmore.cms', null)
logger('com.rainmore.cms.config', INFO)
logger('com.rainmore.cms.Application', INFO)


// Third-party libraries
//logger('org.hibernate', WARN)
logger('org.springframework', WARN)
logger('org.springframework.boot', INFO)
logger('org.springframework.security', WARN)
//logger('org.springframework.scheduling', null)
//logger('org.thymeleaf', WARN)
logger('com.querydsl', WARN)
logger('com.zaxxer', WARN)
logger('org.eclipse.jetty', WARN)

root(level, appenders)
