Config for use with https://monitoror.com/
View status of local dev servers and CI build.

Usage

* download binary to this dir (https://monitoror.com/download/)
  * you may find `justfile` useful
* `> cp .env_template .env`
* Create GH token for Monitoror  
https://github.com/settings/tokens/new?description=Monitoror&scopes=repo:status  
and set it in `.env`
* `> ./monitoror`

Unfortunately, _monitoror_ does not seem to like binary in other location.  
Possible alternative: use Docker image.
