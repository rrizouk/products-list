# products-list

## Technology stack
- Java8
- SpringBoot

### Architecture
- The service is a RESTFUL stateless service and can be used in a distributed architecture such as micro service.

## Building and running the app
- ensure java 8 is installed
- import the project into an IDE e.g. eclipse or intelliJ and build and run the tests

### Running the app
- Application.java is the entry point which will start Spring Boot app on localhost:8080
- use Postman or some other Http client to make REST calls

## endpoints

  URL: http://localhost:8080/products?labelTypeStr=ShowWasNow
  METHOD: GET
  Response: HTTP STATUS: 200
  PAYLOAD RESPONSE:

  {"products":[{"productId":"3459039","title":"Hobbs Bayview Dress, Blue/Multi","nowPrice":"£55","priceLabel":"Was £89, now £55","colorSwatches":[]},{"productId":"3467432","title":"Boden Rosamund Posy Stripe Dress","nowPrice":"£63","priceLabel":"Was £90, now £63","colorSwatches":[{"color":"Navy","rgbColor":"0000FF","skuid":"237334043"},{"color":"Mimosa Yellow","rgbColor":"","skuid":"237334029"}]},{"productId":"3421340","title":"Phase Eight Beatrix Floral Printed Dress, Cream/Red","nowPrice":"£99","priceLabel":"Was £140, now £99","colorSwatches":[]},{"productId":"3428696","title":"Hobbs Kiona Dress, Green","nowPrice":"£74","priceLabel":"Was £149, now £74","colorSwatches":[]},{"productId":"3391561","title":"Phase Eight Katlyn Three Quarter Sleeve Spot Dress, Navy/Ivory","nowPrice":"£59","priceLabel":"Was £99, now £59","colorSwatches":[]}]}


http://localhost:8080/products?labelTypeStr=ShowWasThenNow
http://localhost:8080/products?labelTypeStr=ShowPercDscount


Notes:
  - more test coverage is needed namely integration tests and end to end tests and some unit tests for the ProductsListGateway and ProductListMapper
  - more code refactoring on ProductListMapper such as externalising the hardcode values
  - now price attribute that contains from and to elements was treated as empty for the sake of keeping things simple
  - error scenarios where not covered to keep things simple

