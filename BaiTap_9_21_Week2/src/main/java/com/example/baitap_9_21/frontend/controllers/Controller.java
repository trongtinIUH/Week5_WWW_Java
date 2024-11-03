package com.example.baitap_9_21.frontend.controllers;

import com.example.baitap_9_21.backend.dtos.ProductDTO;
import com.example.baitap_9_21.frontend.models.ProductModel;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Controller", value = "/controller")
public class Controller extends HttpServlet {
    @Inject
    private ProductModel productModel;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "list_products"; // Default action
        }

        switch (action.toLowerCase()) {
            case "list_products":
                listProducts(req, resp);
                break;
            case "find_product":
                findProduct(req, resp);
                break;

            default:
                listProducts(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String action = req.getParameter("action");
        switch (action.toLowerCase()) {
            case "add_product":
                addProduct(req, resp);
                break;
            case "delete_product":
                deleteProduct(req, resp);
                break;

            case "update_product":
                updateProduct(req, resp);
                break;
            default:
                listProducts(req, resp);
        }
    }

    private void listProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<ProductDTO> productDTOS = productModel.getAllProducts();
            req.setAttribute("productDTOS", productDTOS);
        } catch (Exception e) {
            req.setAttribute("error", "Không thể lấy danh sách sản phẩm");
        }
//
    }

    private void findProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                ProductDTO productDTO = productModel.getProductById(id);
                if (productDTO != null) {
                    // Redirect to the product URL with the found ID
                    resp.sendRedirect("http://localhost:8080/BaiTap_9_21-1.0-SNAPSHOT/api/products/" + id);
                    return; // Stop further processing
                } else {
                    req.setAttribute("error", "Không tìm thấy sản phẩm");
                }
            } catch (NumberFormatException e) {
                req.setAttribute("error", "ID sản phẩm không hợp lệ");
            } catch (Exception e) {
                req.setAttribute("error", "Không tìm thấy sản phẩm");
            }
        }
        listProducts(req, resp);
    }

    private void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        ProductDTO productDTO = new ProductDTO(name, description, null, price);
        productModel.addProduct(productDTO);
        resp.sendRedirect("http://localhost:8080/BaiTap_9_21-1.0-SNAPSHOT/api/products");
//        req.setAttribute("productDTOS", productModel.getAllProducts());
//        req.getRequestDispatcher("views/products.jsp").forward(req, resp);
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {

                int id = Integer.parseInt(idParam);
                productModel.deleteProduct(id); // Gọi phương thức xóa
            } catch (NumberFormatException e) {
                req.setAttribute("error", "ID sản phẩm không hợp lệ");
            } catch (Exception e) {
                req.setAttribute("error", "Không thể xóa sản phẩm");
            }
        }
        resp.sendRedirect("http://localhost:8080/BaiTap_9_21-1.0-SNAPSHOT/api/products");

    }

        private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String idParam = req.getParameter("id");

            if (idParam != null && !idParam.isEmpty()) {
                try {
                    int id = Integer.parseInt(idParam);
                    ProductDTO productDTO = productModel.getProductById(id);

                    if (productDTO != null) {
                        String name = req.getParameter("name");
                        productDTO.setName(name);

                        productModel.updateProduct(productDTO); // Gọi API PUT để cập nhật sản phẩm

                        // Kiểm tra xem sản phẩm đã được cập nhật hay chưa (có thể thêm log)
                        System.out.println("Sản phẩm đã được cập nhật: " + name);

                        resp.sendRedirect("http://localhost:8080/BaiTap_9_21-1.0-SNAPSHOT/api/products/" + id);
                        return;
                    } else {
                        req.setAttribute("error", "Không tìm thấy sản phẩm");
                    }
                } catch (NumberFormatException e) {
                    req.setAttribute("error", "ID sản phẩm không hợp lệ");
                } catch (Exception e) {
                    req.setAttribute("error", "Có lỗi xảy ra khi cập nhật sản phẩm");
                }
            }

            listProducts(req, resp);
        }



}
