## Thamiris Vasconsellos Lopes
### Inbev - Desafio Java

 **What you’ll need**
 
 * JDK 1.8 or later
 * Spring Boot
 * Gradle
 * Docker

 ##### Create Credentials - Authentication API instruction
 Method	| Path	| body {JSON} 	| User authenticated {Bearer} | Available from UI
   --- | --- | --- |:---:|:---:|
   GET	| /login	| { "username": "admin", "password": "password"}	| × | ×
  
  OBS: Response Bearer token in headers Authorization
  
 ##### VirtualStore API instruction PRODUCT
  Method	| Path	| Description	| User authenticated {Bearer} | Available from UI
  --- | --- | --- |:---:|:---:|
  GET	| /api/v1/product/{id}	| Get specified product by ID	| ✓ | ×
  GET	| /api/v1/product	| Get specified ALL product	| ✓ | ×
  GET	| /api/v1/product?name={name}	| Get specified product by Name	| ✓ | ×
  GET	| /api/v1/product?orderBy={DESC/ASC}	| Get specified product order by Name	| ✓ | ×
  POST	| /api/v1/product	| Create new product data	| ✓ | ×
  PATCH	| /api/v1/product/{id}?name={name}	| Update specified product name	| ✓ | ×
  DELETE | /api/v1/product/{id}	| Delete specified product data	| ✓ | ×
 




