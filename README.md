# Order Enrichment Demo

### Setup and Running

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/your-username/order-data-demo.git
    cd order-data-demo
    ```

2.  **Build the application JAR:**
    ```bash
    ./mvnw clean install
    ```

3.  **Run the application with Docker:**
    ```bash
    docker compose up
    ```

4.  **Stop the application:**
    ```bash
    docker compose down 
    ```

The application will be accessible at `http://localhost:8080`.

## Example Request

When creating an order, the request body should look like this:

```json
{
  "orderId": 10,
  "customerId": 1,
  "productIds": 1,
  "timestamp": "2025-08-20T12:00:00Z"
}
```
## Running Tests

To run all unit tests:

```bash
./mvnw test
```
