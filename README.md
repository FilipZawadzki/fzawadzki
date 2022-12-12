# fzawadzk
 
First created: 8.12.2022

It's a showcase application concerning backend Parcel delivery to ParcelLockers.

Specification:
There's some logistic company wanting to start delivering their parcels to parcel lockers. Parcels can have 4 statuses - sent, in parcel locker, received and returned. Parcels also have size, deposit box in which they are deposited, sender and receiver.
Parcel lockers have codes, post codes, addresses. Clients can send packages and receive them. Their basic informations are email, name, last name. 

Service should be able to:
a) post a parcel
b) deliver a parcel
c) change statuses of all parcels which have status 'in parcel locker' for 48h+
