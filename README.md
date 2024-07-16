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


POST /api/wishlist/v1/:clientId/products
```bash
curl --location 'http://localhost:8080/api/wishlist/v1/:clientId/products' \
--header 'Content-Type: application/json' \
--data '{
    "id": "d3b8373b-accd-443d-ad8a-dad326e494c9",
    "name": "book",
    "image": "https://ecommerce.com/produts/images/book.png",
    "value": "100.00",
    "link": "https://ecommerce.com/productId"
}'
```
    
GET /api/wishlist/v1/{clientId}/products
```bash
curl --location 'http://localhost:8080/api/wishlist/v1/:clientId/products'
```
    
DELETE /api/wishlist/v1/{clientId}/products
```bash
curl --location --request DELETE 'http://localhost:8080/api/v1/wishlist/:clientId/products' \
--header 'Content-Type: application/json' \
--data '{
        "id": "c271cdfb-f5a1-4205-b462-f73dbf332e82",
        "name": "book",
        "image": "https://ecommerce.com/produts/images/book.png",
        "value": "100.00",
        "link": "https://ecommerce.com/productId"
    }
'
```

## Technical Debts
- [ ] Implement endpoint to verify if a product is on wishlist
- [X] Secure API
- [ ] Other Unit Tests
- [ ] Integration Tests
- [ ] OpenAPI/Swagger Documentation
- [ ] GitHub Actions Integrations
- [ ] Observability issues
- [ ] ...