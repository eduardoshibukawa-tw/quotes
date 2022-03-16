# Quotes

Quotes is a project to demonstrate the utilization of spring cloud configuration, and spring cloud bus. 

This project contains two client services, a spring cloud configuration server, and another github repository
as the configuration source.

The clients service only have a bootstrap configuration, that we need in order to find the real configuration 
that's loaded from [quotes-configuration](https://github.com/eduardoshibukawa-tw/quotes-configuration), we also need 
to configure our clients to use spring cloud bus, we are doing this in the github since all services 
will share the same configuration.


Boostrap example:
```yml
### bootstrap.yml

spring:
  application:
    name: yoda

  cloud:
    config:
      # Spring cloud config server
      uri: http://localhost:8888 
      # This will search for yoda.yml, settings.yml, toggles.yml in github configuration
      name: ${spring.application.name}, settings, toggles
```

Shared configuration:
```yml
### settings.yml

spring:
  cloud:
    ### Enable spring cloud bus in client service
    bus:
      enabled: true
      refresh:
        enabled: true

  ## RabbitMQ configuration
  rabbitmq:
    host: localhost
    port: 5672
    username: springcloud
    password: 123456

### Enable actuator endpoints
management:
  security:
    enabled: false
  endpoints:
    web:
      exposure:
        include: "*"
```

We need to configure the spring cloud config serve service, to find the configuration source in our case the github,
and to find the rabbitMQ since we are using spring cloud bus to notify our clients when our configuration changed.

```yml
### application.yml

server:
  port: 8888

spring:
  cloud:
    config:
      server:
        git:
          ## Github URI 
          uri: https://github.com/eduardoshibukawa-tw/quotes-configuration
          ## Branch name that we will load our configuration 
          default-label: main
          clone-on-start: true
          ## Folders which we will try to find the configuration
          ## example: toggles/*.yml
          search-paths:
            - '{application}'
            - toggles
    ## Enable spring cloud bus
    bus:
      enabled: true
  ## RabbitMQ configuration
  rabbitmq:
    host: localhost
    port: 5672
    username: springcloud
    password: 123456
```
## Running

```bash
docker-compose up -d
sh startup.sh
```

## Testing

```bash
### Github configuration: https://github.com/eduardoshibukawa-tw/quotes-configuration

eduardo.shibukawa in ~
### Julius service call
❯ curl localhost:8081/quotes | jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   100    0   100    0     0    961      0 --:--:-- --:--:-- --:--:--  1041
[
  "The discount is bigger if I don’t buy anything.",
  "You lost $10? That’s $10 worth of dollars."
]
eduardo.shibukawa in ~
❯ curl localhost:8081/quote
The discount is bigger if I don’t buy anything.%
eduardo.shibukawa in ~
❯ curl localhost:8081/quote
You lost $10? That’s $10 worth of dollars.%

### Yoda service calls
❯ curl localhost:8080/quotes | jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   109    0   109    0     0   1107      0 --:--:-- --:--:-- --:--:--  1211
[
  "Pass on what you have learned.",
  "Do or do not. There is no try.",
  "You must unlearn what you have learned."
]
eduardo.shibukawa in ~
❯ curl localhost:8080/quote
Pass on what you have learned.%
eduardo.shibukawa in ~
❯ curl localhost:8080/quote
You must unlearn what you have learned.%

### Removing a quote from yoda service, then call the command above
eduardo.shibukawa in ~
❯ curl -X POST http://localhost:8888/monitor -H "X-Github-Event: push" -H "Content-Type: application/json" -d '{"commits": [{"modified": ["application.properties"]}]}'

["*"]%
eduardo.shibukawa in ~ took 3s
❯ curl localhost:8080/quotes | jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    67    0    67    0     0   3983      0 --:--:-- --:--:-- --:--:--  7444
[
  "Pass on what you have learned.",
  "Do or do not. There is no try."
]

### Change param quotes to false in configuration, then call the command above
eduardo.shibukawa in ~
❯ curl -X POST http://localhost:8888/monitor -H "X-Github-Event: push" -H "Content-Type: application/json" -d '{"commits": [{"modified": ["application.properties"]}]}'
["*"]%

### Yoda service
eduardo.shibukawa in ~
❯ curl localhost:8080/quote
Default quote%

### Julius service
eduardo.shibukawa in ~
❯ curl localhost:8081/quote
Default quote%
```
