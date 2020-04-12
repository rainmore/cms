# Content Managerment System

A simple CMS demonstrats the usage of Spring Boot, Angular and Docker. 

## Config MYSQL

```
CREATE DATABASE rainmore_cms;

CREATE USER 'rainmore_cms'@'localhost' IDENTIFIED BY 'spEcabu#7tr@';
CREATE USER 'rainmore_cms'@'%' IDENTIFIED BY 'spEcabu#7tr@';

GRANT ALL PRIVILEGES ON rainmore_cms.* TO 'rainmore_cms'@'localhost';
GRANT ALL PRIVILEGES ON rainmore_cms.* TO 'rainmore_cms'@'%';

FLUSH PRIVILEGES;

CREATE DATABASE rainmore_cms_dev;

CREATE USER 'rainmore_cms_dev'@'localhost' IDENTIFIED BY 'rainmore_cms_dev';
CREATE USER 'rainmore_cms_dev'@'%' IDENTIFIED BY 'rainmore_cms_dev';

GRANT ALL PRIVILEGES ON rainmore_cms_dev.* TO 'rainmore_cms_dev'@'localhost';
GRANT ALL PRIVILEGES ON rainmore_cms_dev.* TO 'rainmore_cms_dev'@'%';


FLUSH PRIVILEGES;
```