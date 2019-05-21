# PBKK-Spring-Accounting

## Contributors.

1. **Alvin Tanuwijaya (05111640000021)**

2. **Nurlita Dhuha F.     (05111640000092)**

3. **Chrisnady Anggara  (05111640000153)**

---

## End Point

**Reporting**
---

| Method | End Point | Parameter | Privilege | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
|GET| /receipt | - | User | Get all receipt. |
|GET| /receipt/{id} | - | User | Get receipt by id. |
|GET| /receipt | customerId, dateStart, dateEnd | User ||
|GET| /receipt | restaurant, dateStart, dateEnd| User ||
|GET| /receipt | driver, dateStart, dateEnd| User ||
|GET| /cashflow | customer | User ||
|GET| /cashflow | customer, dateStart, dateEnd | User ||
|GET| /cashflow | restaurant | User ||
|GET| /cashflow | restaurant, dateStart, dateEnd| User ||
|GET| /cashflow | customer, restaurant | User ||
|GET| /cashflow | driver | User ||
|GET| /cashflow | driver, dateStart, dateEnd| User ||
|GET| /delivery | | User ||
|GET| /delivery/{driverId} |  | User ||
|GET| /delivery/{driverId} | dateStart, dateEnd | User ||

**Order**
---

| Method | End Point | Parameter | Privilege | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
|POST| /receipt/create | receiptDetail | User ||

Format receiptDetail:
```
{  
    "transaksi":{  
        "transaksiId": ,  
        "transaksiStatus": ,  
        "restaurantId": ,  
        "customerId":   
    },  
    "detailPembayaran":[  
        {  
        "transaksiId": ,  
        "namaMenu": ,  
        "hargaMenu": ,  
        "jumlahMenu":  
        },  
        {  
        "transaksiId": ,  
        "namaMenu": ,  
        "hargaMenu": ,  
        "jumlahMenu":  
        }  
    ]  
}  
```
**Payment**
---

| Method | End Point | Parameter | Privilege | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
|POST| /cashflow/create | id, source, sourceId, destination, destinationId, jumlahUang | User ||

**Delivery**
---

| Method | End Point | Parameter | Privilege | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
|POST| /delivery/create | id, driverId, transaksiId, ongkir | User ||
