![img/img.png](img/img.png)

## 1. Viết API CRUD xe
[GET] /api/vehicles
![img/img_1.png](img/img_1.png)

[GET] /api/vehicles/{id}
![img/img_2.png](img/img_2.png)

[POST] /api/vehicles
![img/img_3.png](img/img_3.png)

[PUT] /api/vehicles/{id}
![img/img_4.png](img/img_4.png)

[DELETE] /api/vehicles/{id}
![img/img_5.png](img/img_5.png)

## 2. Viết API tìm kiếm các xe (hãng xe, năm sản xuất, giá tiền, người sở hữu)
* Dữ liệu mẫu bảng brand
![img/img_13.png](img/img_13.png)

* Dữ liệu mẫu bảng vehicle
![img/img_14.png](img/img_14.png)

[POST] /api/vehicles/search
![img/img_7.png](img/img_7.png)

## 3. Xuất ra các xe có giá giá tiền > 10.000.000 và tên hãng bắt đầu bằng "S", nếu giá tiền <= 10.000.000 thì chỉ in ra các hãng xe thuộc loại "BUS"

[GET] /api/vehicles/filter
![img/img_12.png](img/img_12.png)
