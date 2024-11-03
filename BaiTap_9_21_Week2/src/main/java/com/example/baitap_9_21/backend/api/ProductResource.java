package com.example.baitap_9_21.backend.api;

import com.example.baitap_9_21.backend.business.ProductBeanRemote;
import com.example.baitap_9_21.backend.business.ProductPriceRemote;
import com.example.baitap_9_21.backend.dtos.ProductDTO;
import com.example.baitap_9_21.backend.repositories.entities.Product;
import com.example.baitap_9_21.backend.repositories.entities.ProductPrice;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;

@Path("/products")
public class ProductResource {

    @EJB
    private ProductBeanRemote productBeanRemote;

    @EJB
    private ProductPriceRemote productPriceRemote;

    @GET
    public Response getProducts() {
        return Response.ok(productBeanRemote.getProducts()).build();
    }
    @GET
    @Path("/{id}")
    public Response getProduct( @PathParam("id") int id) {
        return Response.ok(productBeanRemote.getProduct(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(ProductDTO productDTO) {
        try {
            Product product = new Product(productDTO.getName(), productDTO.getDescription(), productDTO.getImgPath());
            ProductPrice price = new ProductPrice();
            price.setValue(productDTO.getPrice());
            price.setProduct(product);
            price.setState((byte) 1);

            price.setApplyDate(LocalDate.now());
            productBeanRemote.add(product); // Gọi phương thức add mà không cần trả về
            productPriceRemote.add(price);
        }
        catch (Exception e) {
            System.out.println("Error in addProduct"+ e);
//            e.printStackTrace();
        }
        return Response.status(Response.Status.CREATED).build(); // Trả về phản hồi 201 (Created)
    }
    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") Long id) {
        try {
            // Kiểm tra xem sản phẩm có tồn tại không
            Product product = productBeanRemote.getProduct(id);
            if (product != null) {
                productBeanRemote.delete(product); // Gọi phương thức xóa
                return Response.noContent().build(); // Trả về phản hồi 204 No Content
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Sản phẩm không tìm thấy").build(); // Trả về 404 nếu không tìm thấy sản phẩm
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Có lỗi xảy ra: " + e.getMessage()).build(); // Trả về 500 nếu có lỗi
        }
    }

    //hàm update product
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(ProductDTO productDTO) {
        try {
            Product product = productBeanRemote.getProduct(productDTO.getId());
            if (product != null) {
                product.setName(productDTO.getName());
                productBeanRemote.update(product); // Chắc chắn rằng trong ProductBeanRemote, phương thức update đã được triển khai với entityManager.merge()
                return Response.noContent().build(); // Trả về 204 No Content để biểu thị cập nhật thành công
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Sản phẩm không tìm thấy").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Có lỗi xảy ra: " + e.getMessage()).build();
        }
    }

}
