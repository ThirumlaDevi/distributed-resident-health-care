# Resident Health Care

## Getting started

To make it easy for you to get started with GitLab, here's a list of recommended next steps.

Already a pro? Just edit this README.md and make it your own. Want to make it easy? [Use the template at the bottom](#editing-this-readme)!

## Steps to compile and up all of the services
### Pre-requisites
- mavan 
- docker 
- java

### Commands
```
mvn clean install
docker-compose build
docker-compose up
```

## Pharmaceutical services
### Command to exec into mysql and check the tables and database in it
```
docker exec -it resident-health-care-database-1 mysql -uuser -puserpwd 
```

### List of services
- resident-health-care-td-pharmacy-service-1-1
- resident-health-care-td-pharmacy-service-2-1
- resident-health-care-yan-pharmacy-service-1-1
- resident-health-care-yan-pharmacy-service-2-1
- resident-health-care-yue-pharmacy-service-1-1
- resident-health-care-yue-pharmacy-service-2-1

### Curl requests to add and get list of medicines from pharmaceutical services

- post medicine data to pharma services
```
curl -X POST http://<resident-pharma-host>:<port>/v1/pharmacy/medicine
   -H 'Content-Type: application/json'
   -d '{"name":<medicine-name>,"price":<medicine-price>,"quantity":<number-of-medicines-added-to-the-pharmacy-stock>}'
```

*Examples*
_Note: need to find a way to do the same via eureka service and this will be done in doctor and residance service end_
```
curl -X POST http://localhost:8081/v1/pharmacy/medicine \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d '{"name":"medicineB","price":1.20,"quantity":20}'

curl -X POST http://localhost:8083/v1/pharmacy/medicine \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d '{"name":"medicineB","price":1.20,"quantity":20}'

curl -X POST http://localhost:8085/v1/pharmacy/medicine \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d '{"name":"medicineB","price":1.20,"quantity":20}'
```

- add medicines data to pharma services
```
curl -X POST http://<resident-pharma-host>:<port>/v1/pharmacy/medicine
   -H 'Content-Type: application/json'
   -d '{"name":<medicine-name>,"price":<medicine-price>,"quantity":<number-of-medicines-added-to-the-pharmacy-stock>}'
```

*Examples*
_Note: need to find a way to do the same via eureka service and this will be done in doctor and residance service end_
```
curl -X POST http://localhost:8085/v1/pharmacy/medicines \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d '[{"name":"medicineB","price":1.20,"quantity":20}, {"name":"medicineB","price":3.20,"quantity":30}, {"name":"medicineA","price":1.20,"quantity":20}]'
```

- get medicine data from respective pharma services
```
CURL -XGET http://<resident-pharma-host>:<port>/v1/pharmacy/medicines
```

_Examples_
```
CURL -XGET http://localhost:8082/v1/pharmacy/medicines
CURL -XGET http://localhost:8084/v1/pharmacy/medicines
CURL -XGET http://localhost:8086/v1/pharmacy/medicines
```

### Curl requests to add and get order from pharmaceutical services
- *Place order*
```
curl -X POST http://<resident-pharma-host>:<port>/v1/pharmacy/order
   -H 'Content-Type: application/json'
   -d '[{"name":<medicine-name>,"price":<medicine-price>,"quantity":<stock-needed>}]'
```

*Examples*
```
curl -X POST http://localhost:8081/v1/pharmacy/order \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d '[{"name":"medicineB","price":1.20,"quantity":20}]'

curl -X POST http://localhost:8083/v1/pharmacy/order \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d '[{"name":"medicineB","price":1.20,"quantity":20}]'

curl -X POST http://localhost:8085/v1/pharmacy/order \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d '[{"name":"Omeprazole","price":1.20,"quantity":20}, {"name":"medicineB","price":1.20,"quantity":20}]'
```

- Cancel order
```
curl -X DELETE http://<resident-pharma-host>:<port>/v1/pharmacy/order
   -H 'Content-Type: application/json'
   -d '{"medicines":[{"medicineName":"Omeprazole","stockAvailable":0,"isNeededStockAvailable":false,"price":0.6}, {"medicineName":"medicineB","stockAvailable":0,"isNeededStockAvailable":false,"price":null}]}'
```

*Examples*
```
curl -XDELETE http://localhost:8081/v1/pharmacy/order \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d '[{"name":"medicineB","price":1.20,"quantity":20}]'

curl -XDELETE http://localhost:8083/v1/pharmacy/order \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d '[{"name":"medicineB","price":1.20,"quantity":20}]'

curl -XDELETE http://localhost:8085/v1/pharmacy/order \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d '{"medicines":[{"medicineName":"Omeprazole","stockAvailable":10,"isNeededStockAvailable":false,"price":0.6}, {"medicineName":"medicineB","stockAvailable":0,"isNeededStockAvailable":false,"price":null}]}'
```

*Examples*
```
CURL -XDELETE http://localhost:8082/v1/pharmacy/order/1
CURL -XDELETE http://localhost:8084/v1/pharmacy/order/1
CURL -XDELETE http://localhost:8086/v1/pharmacy/order/1
```

### Command to SSH into corresponding mysql database

```
docker exec -it <container-id> mysql -uuser -puserpwd 
```
docker exec -it 09c27235cf35 mysql -uuser -puserpwd 
## Integrate with your tools

- [ ] [Set up project integrations](https://gitlab.com/comp30220/2022/resident-health-care/-/settings/integrations)

## Collaborate with your team

- [ ] [Invite team members and collaborators](https://docs.gitlab.com/ee/user/project/members/)
- [ ] [Create a new merge request](https://docs.gitlab.com/ee/user/project/merge_requests/creating_merge_requests.html)
- [ ] [Automatically close issues from merge requests](https://docs.gitlab.com/ee/user/project/issues/managing_issues.html#closing-issues-automatically)
- [ ] [Enable merge request approvals](https://docs.gitlab.com/ee/user/project/merge_requests/approvals/)
- [ ] [Automatically merge when pipeline succeeds](https://docs.gitlab.com/ee/user/project/merge_requests/merge_when_pipeline_succeeds.html)

## Test and Deploy

Use the built-in continuous integration in GitLab.

- [ ] [Get started with GitLab CI/CD](https://docs.gitlab.com/ee/ci/quick_start/index.html)
- [ ] [Analyze your code for known vulnerabilities with Static Application Security Testing(SAST)](https://docs.gitlab.com/ee/user/application_security/sast/)
- [ ] [Deploy to Kubernetes, Amazon EC2, or Amazon ECS using Auto Deploy](https://docs.gitlab.com/ee/topics/autodevops/requirements.html)
- [ ] [Use pull-based deployments for improved Kubernetes management](https://docs.gitlab.com/ee/user/clusters/agent/)
- [ ] [Set up protected environments](https://docs.gitlab.com/ee/ci/environments/protected_environments.html)

## Contributing
State if you are open to contributions and what your requirements are for accepting them.

For people who want to make changes to your project, it's helpful to have some documentation on how to get started. Perhaps there is a script that they should run or some environment variables that they need to set. Make these steps explicit. These instructions could also be useful to your future self.

You can also document commands to lint the code or run tests. These steps help to ensure high code quality and reduce the likelihood that the changes inadvertently break something. Having instructions for running tests is especially helpful if it requires external setup, such as starting a Selenium server for testing in a browser.

## Authors and acknowledgment
Show your appreciation to those who have contributed to the project.

## License
For open source projects, say how it is licensed.

## Project status
If you have run out of energy or time for your project, put a note at the top of the README saying that development has slowed down or stopped completely. Someone may choose to fork your project or volunteer to step in as a maintainer or owner, allowing your project to keep going. You can also make an explicit request for maintainers.
