# Wishlist
Sample REST API with Spring Boot, MongoDB and Docker over a Clean Architecture approach

## Briefing
This is an api that tries to represent a wishlist from e-commerces that are widely spread on web.

## Steps to Setup

**1. Clone the application**

```bash
https://github.com/dlsalbino/wishlist.git
```

**2. Create MongoDB instance**
```bash
docker run --name mongodb -p 27017:27017 -d mongodb/mongodb-community-server:7.0.11-ubi8
```

**3. Run the app**

```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080>.


## Explore Rest APIs
The app defines following these API's endpoints:

POST /api/wishlist/v1/auth  
In `Authorization` you can use 'username:password' or 'thor:playboy' encrypted by Base64 algorithm. ;-)
```bash 
curl --location --request POST 'http://localhost:8080/api/wishlist/v1/auth' \
--header 'Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ='
```

POST /api/wishlist/v1/:customerId/products
```bash
curl --location 'http://localhost:8080/api/wishlist/v1/:customerId/products' \
--header 'Authorization: Bearer {token}' \
--header 'Content-Type: application/json' \
--data '{
    "id": "fe343ffe-528a-4499-925a-bdbe2295a46b",
    "name": "book",
    "image": "https://ecommerce.com/produts/images/book.png",
    "value": 10.00,
    "link": "https://ecommerce.com/productId"
}'
```
    
GET /api/wishlist/v1/:customerId/products
```bash
curl --location 'http://localhost:8080/api/wishlist/v1/:customerId/products'
```
    
DELETE /api/wishlist/v1/:customerId/products/:productId
```bash
curl --location --request DELETE 'http://localhost:8080/api/wishlist/v1/:customerId/products/:productId' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer {token}'
```

## Swagger Documentation
Another way to explore the API is, when it is running you use the Swagger Documentation existent [here](http://localhost:8080/api/wishlist/swagger-ui/index.html).
