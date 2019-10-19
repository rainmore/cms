# Content Managerment System

A simple CMS demonstrats the usage of Spring Boot, Angular and Docker. 

## DB Config
Install MongoDB
...

```
// create admin user
db.createUser({user: "cms-admin", pwd: "cms-admin", roles:[{role: "readWrite", db: "cms-admin"}]})

// verify the username and the password
use admin
db.auth("cms-admin", "cms-admin")

use cms-admin
```