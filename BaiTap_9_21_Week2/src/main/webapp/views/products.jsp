
<%@ page import="java.util.List" %>
<%@ page import="com.example.baitap_9_21.backend.dtos.ProductDTO" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Danh sách sản phẩm</title>
</head>
<body>
<h1>Danh sách sản phẩm</h1>

<div>
  <form action="controller" method="get">
    <input type="hidden" name="action" value="find_product">
    <label for="productId">Tìm sản phẩm theocccc ID:</label>
    <input type="text" id="productId" name="id" required>
    <input type="submit" value="Tìm kiếm">
  </form>
</div>
<div>
  <form action="Controller" method="post">
    <input type="hidden" name="action" value="add_product">
    <label for="productName">Tên sản phẩm:</label>
    <input type="text" id="productName" name="name" required>
    <label for="productPrice">Giá sản phẩm:</label>
    <input type="number" id="productPrice" name="price" required>
    <label for="productDescription">Mô tả:</label>
    <input type="text" name="description" id="productDescription">
    <input type="submit" value="Thêm sản phẩm">
  </form>
</div>
<%
  String error = (String) request.getAttribute("error");
  if (error != null) {
    out.println("<p style='color: red;'>" + error + "</p>");
  }

  ProductDTO foundProductDTO = (ProductDTO) request.getAttribute("foundProduct");
  if (foundProductDTO != null) {
    out.println("<h3>Kết quả tìm kiếm:</h3>");
    out.println("<p>" + foundProductDTO + "</p>");
  }
%>

<h2>Tất cả sản phẩm:</h2>
<ul>
  <%
    List<ProductDTO> productDTOS = (List<ProductDTO>) request.getAttribute("productDTOS");
    if (productDTOS != null) {
      for (ProductDTO productDTO : productDTOS) {
        out.println("<li>" + productDTO + "</li>" +
                "<button title='xóa' onclick='deleteProduct(" + productDTO.getId() + ")'>Xóa</button>");
      }
    }
  %>
</ul>

<p><a href="controller?action=list_products">Làm mới danh sách</a></p>
</body>
</html>

<script>
  function deleteProduct(productId) {
    if (confirm("Bạn có chắc chắn muốn xóa sản phẩm này hong?")) {
      console.log("xóa ne");
      fetch('controller?action=delete_product&id=' + productId, {
        method: 'POST'
      })
              .then(response => {
                if (response.ok) {
                  // Tải lại trang hoặc cập nhật danh sách sản phẩm
                  console.log("xóa oke r nè");
                  location.reload();
                  // fetch('controller?action=list_products',{
                  //     method:'GET'
                  // })
                  // .then(response => {
                  //     if (response.ok) {
                  //         console.log("oke r ne");
                  //         return response.json(); // Giả sử server trả về JSON
                  //     } else {
                  //         throw new Error("Failed to fetch products");
                  //     }
                  // })
                  // .then(data => {
                  //     console.log("dữ liệu sản phẩm mới: ",data);
                  //     updateProductList(data); // Gọi hàm để cập nhật danh sách sản phẩm
                  // })
                  // .catch(error => {
                  //     console.error("Error fetching products:", error);
                  // });
                } else {
                  alert("Có lỗi xảy ra khi xóa sản phẩm.");
                }
              })
              .catch(error => {
                console.error("Lỗi:", error);
                alert("Có lỗi xảy ra khi xóa sản phẩm.");
              });
    }
  }

  function updateProductList(products) {
    const productList = document.querySelector('ul');
    productList.innerHTML = ''; // Xóa danh sách hiện tại

    products.forEach(product => {
      const li = document.createElement('li');
      li.textContent = `ID: ${product.id}, Nameádasd: ${product.name}, Description: ${product.description}, Price: ${product.price}`;
      productList.appendChild(li);
    });
  }
</script>
