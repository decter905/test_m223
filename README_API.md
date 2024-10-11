__SHORT INTRODUCTION INTO THE API__

The supplier application is running as a service in the background. You can communicate with the application through the SupplierApi-Class.

The API which has been supplied to you in this directory should handle the traffic to the suppliers application; you don't need to worry about the correctness of anything related to both the API or the underlying application / service.

Methods in the API:

- `public Pair<String, Boolean> enterOrder(String ISBN, int quantity)`
- `public Boolean rollbackOrder(String orderId)`

For more information about the function of these methods check their JavaDoc.
