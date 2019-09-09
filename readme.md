## Purpose
This is a training app used to exercise higher level (non-unit) app testing. Given a vin, it will identify the vin's history, make, model and year, and then attempt to match it with existing vehicles available for sale and give a recommended price.

## Architecture
There are 3 collaborating services, presenting different testing challenges:
  * history - cannot be injected with data, data will change over time
  * mommy - make mode year. Data cannot be modified, but is stable. This service is slow.
  * listings - can make and delete new listings for testing purposes. Tests from multiple workstations will collide initially

## Running
  * Start up: history, listings, mommy, and vinkenstein
  * navigate to http://localhost:8080/index.html

## Reference:
  * https://spring.io/guides/gs/testing-web/
  * https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html