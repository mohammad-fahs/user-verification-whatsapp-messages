# Verifying users using WhatsApp messaging

# Introduction:

I needed a cost-effective and straightforward method for user account verification. Initially, I considered leveraging Twilio's free messaging service. However, a hurdle emerged as this service is blocked in some countries, resulting in messages being sent via the Twilio API but not received. 

To overcome this limitation, I explored Twilio's WhatsApp messaging service. Yet, this alternative demanded account verification and a deposit in the Twilio account, introducing additional complexities.

In response, I devised a solution that utilizes the WhatsApp-web ava script library to automate the sending of verification messages through my personal WhatsApp account. This was achieved by implementing an API with Express.js. Now, when verifying a user, the system sends a message to the provided number containing a string of digits for account validation. 

This approach not only eliminates the cost associated with Twilio but also ensures a more reliable and accessible user verification process, addressing the challenges posed by regional restrictions on messaging services.

# Java Express application

the folder named node express WhatsApp messenger contains the java script responsible for connecting to WhatsApp and sending the messages.

to run the project move to the folder containing the  `package.json` and then run the following commands (node must be installed)

```java
~ npm install 
~ npm start
```
![Untitled](https://github.com/mohammad-fahs/user-verification-whatsapp-messages/assets/109221274/d6fbdfb1-efcc-41dc-8a62-ae9e35d3c587)


### Connect WhatsApp

after application stated it is running on the port 3000 now call the following APIs to be able to send messages in automated way

1- scan the QR code in your WhatsApp : `localhost:3000/qrcode`
![Untitled 1](https://github.com/mohammad-fahs/user-verification-whatsapp-messages/assets/109221274/6d89fda3-744a-4d88-b1dc-5eb50785d658)


2- call the status API to verify that the client is active: `localhost:3000/status` try this api multiple times until the status is ready or you can check the command line 
![Untitled 2](https://github.com/mohammad-fahs/user-verification-whatsapp-messages/assets/109221274/930b1ad0-299c-4559-bd17-5b04148e2dba)
![Untitled 3](https://github.com/mohammad-fahs/user-verification-whatsapp-messages/assets/109221274/8b38348f-3b40-4ee5-8c0f-fff1c0af5d15)


3- test sending a message by calling the sender API `localhost:3000/send-message` and pass the number and the message that will be sent
![Untitled 4](https://github.com/mohammad-fahs/user-verification-whatsapp-messages/assets/109221274/29accc2f-09c2-4c42-be13-a60b9d24bd3f)

# Spring boot application

now we need to create a spring boot application to create a user and test the verification process 

to test the application I have created 3 APIs

1- create a user and check if a message sent containing the code 
![Untitled 5](https://github.com/mohammad-fahs/user-verification-whatsapp-messages/assets/109221274/fa435e32-7bc2-4f5f-862d-ecc3fcce85de)
![Untitled 6](https://github.com/mohammad-fahs/user-verification-whatsapp-messages/assets/109221274/6865c56c-78c0-411a-99c6-3050efeaa1cf)


2- API to verify the user after entering the correct code  
![Untitled 7](https://github.com/mohammad-fahs/user-verification-whatsapp-messages/assets/109221274/c24ddf29-b029-4ac4-ac0d-b540b42508cc)

3- list the users and check if the user is verified
![Untitled 8](https://github.com/mohammad-fahs/user-verification-whatsapp-messages/assets/109221274/d36b8730-252d-481b-918a-04470edc47a7)


# Remarks:

→ keeping the Node JS application running locally is not a good idea deploying it is better you can use railway then you just need to update the link in the java spring boot application verification sender server from [localhost:3000](http://localhost:3030) into the link provided by the deployed service 

→ don’t ever save the passwords without hashing but because this is not the goal o the application I saved them without hashing
