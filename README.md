# ParcelServiceApp
 
First created: 8.12.2022

It's a showcase application concerning backend Parcel delivery to ParcelLockers.

Used tech stack: Java/Spring, MySQL, H2/JPA, Mockito, JUnit.

Specification:
There's some logistic company wanting to start delivering their parcels to parcel lockers. Parcels can have 4 statuses - sent, in parcel locker, received and returned. Parcels also have size, deposit box in which they are deposited, sender and receiver.
Parcel lockers have codes, post codes, addresses. Clients can send packages and receive them. Their basic informations are email, name, last name. 

Service should be able to:
a) post a parcel
b) deliver a parcel and send confirmation email to the receiver
c) change statuses of all parcels which have status 'in parcel locker' for 48h+

To configure change in .yml files desired properties for your own email server and credentials + db settings

To test you can use PostMan. Exported collection is available in the main folder.

To open app you can run run.bat (required Maven and Java JDK).
