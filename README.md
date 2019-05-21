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
|GET| /receipt | - |  | Get all receipt. |
|GET| /receipt | customer | - ||
|GET| /receipt | customer, dateStart, dateEnd | - ||
|GET| /receipt | restaurant | - ||
|GET| /receipt | restaurant, dateStart, dateEnd| - ||
|GET| /receipt | customer, restaurant | - ||
|GET| /receipt | driver | - ||
|GET| /receipt | driver, dateStart, dateEnd| - ||
|GET| /cashflow | customer | - ||
|GET| /cashflow | customer, dateStart, dateEnd | - ||
|GET| /cashflow | restaurant | - ||
|GET| /cashflow | restaurant, dateStart, dateEnd| - ||
|GET| /cashflow | customer, restaurant | - ||
|GET| /cashflow | driver | - ||
|GET| /cashflow | driver, dateStart, dateEnd| - ||

**Order**
---

| Method | End Point | Parameter | Privilege | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
|POST| /receipt/create | receiptDetail | - ||

Format receiptDetail:
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

**Payment**
---

| Method | End Point | Parameter | Privilege | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
|POST| /cashflow | receiptDetail | - ||

**Delivery**
---

| Method | End Point | Parameter | Privilege | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
|POST| /delivery/create | id, driverId, transaksiId, ongkir | - ||
