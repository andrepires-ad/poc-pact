# Pact Broker with Consumer and Provider services


## Minikube scripts available
 
To start Pact Broker

```jshelllanguage
$ kubectl config use-context minikube
$ kubectl apply -f .minikube/postgres.yaml
$ kubectl apply -f .minikube/pact-broker.yaml
```

## Publish Consumer contracts

The contract is defined as a unit test. See `com.appdirect.pact.consumer.ConsumerProductsTest` 
To publish the contract into the Pact Broker, you must execute the following command. 
   
```jshelllanguage
$ cd consumer-service
$ ./gradlew publishPact 
```

### Pipeline observation
For continuous integration, the contract would only be published on release candidates branches, or master branch.

## Verify the contracts published on the Provider

The provider service should verify the contracts for any new consumer. In that case, it must explicitly provide the *tag* and guide the consumers, providing the test data. 

To verify the contract with all the consumers, execute the following command:

```jshelllanguage
$ cd provider-service
$ ./gradlew pactVerify
```

Note that this command will:
- generate the docker image locally
- run the docker container
- wait for the docker container to be ready
- perform the pact verifications
- stop the docker container 

### Pipeline observation
The pact verification should run on all pipeline executions.

### Pact consumer data example

Here is an example of how the Provider will provide the test data

- Given the provider is **"provider-service"** and the HTTP Header has "Pact-Request" and the user requested "POST /products", the response body will be:

```json
[
	{
		"id": "id-1",
		"name": "name-1"
	},
	{
        "id": "id-2",
        "name": "name-2"
	},
    {
		"id": "id-3",
		"name": "name-3"
	}
]
```
  


