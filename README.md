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


Tìm kiếm danh sách các phương tiện dựa trên các tiêu chí được cung cấp.
![img/img_6.png](img/img_6.png)

[GET] /api/vehicles/search?brandName=Toyota
![img/img_7.png](img/img_7.png)

[GET] /api/vehicles/search?year=2011
![img/img_8.png](img/img_8.png)

[GET] /api/vehicles/search?price=12000000
![img/img_9.png](img/img_9.png)

[GET] /api/vehicles/search?owner=Bien
![img/img_10.png](img/img_10.png)

[GET] /api/vehicles/search?brandName=Toyota&year=2015&price=6000000&owner=Bien
![img/img_11.png](img/img_11.png)

## 3. Xuất ra các xe có giá giá tiền > 10.000.000 và tên hãng bắt đầu bằng "S", nếu giá tiền <= 10.000.000 thì chỉ in ra các hãng xe thuộc loại "BUS"

[GET] /api/vehicles/filter
![img/img_12.png](img/img_12.png)
