# BiProductCollector
Spring Boot project which is collecting product list via http request

- To fetch all products(Max 5 products for each different type as default) : 
/products/fetchAll/{search}

- To fetch specific product [ProductTypes: Book, Album] : 
/products/fetch/{productType}/{search}

- To performance check for fetchAll [fetchAll http request should be invoked before] : 
/actuator/metrics/http.server.requests?tag=performance:products

- To performance check for fetch [fetch http request be invoked before] : 
/actuator/metrics/http.server.requests?tag=performance:product

- To health check : 
/actuator/

Product limit : 
search.limit=5
