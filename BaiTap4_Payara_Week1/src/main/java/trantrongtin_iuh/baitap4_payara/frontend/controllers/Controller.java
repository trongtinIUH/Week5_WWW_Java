package trantrongtin_iuh.baitap4_payara.frontend.controllers;


import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import trantrongtin_iuh.baitap4_payara.backend.dtos.UserDTO;
import trantrongtin_iuh.baitap4_payara.frontend.models.UserModel;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Controller", value = "/controller")
public class Controller extends HttpServlet {

    @Inject
    private UserModel userModel;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String action = req.getParameter("action");
        if(action == null) {
            action = "list_users";
        }
        switch (action.toLowerCase()) {
            case "list_users":
                listUsers(req, resp);
                break;
            case "find_user":
                findUser(req, resp);
                break;
            default:
                listUsers(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String action = req.getParameter("action");
    switch (action.toLowerCase()) {
        case "add_user":
            addUser(req, resp);
            break;
        case "delete_user":
            deleteUser(req, resp);
            break;
        case "update_user":
            updateUser(req, resp);
            break;
        default:
            listUsers(req, resp);
    }

    }

    private void listUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<UserDTO> users = userModel.getAllUsers();
            resp.sendRedirect("http://localhost:8080/BaiTap4_Payara-1.0-SNAPSHOT/api/users");
        } catch (Exception e) {
            req.setAttribute("error", "Không thể lấy danh sách sản phẩm");
        }
    }

    private void findUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if(idParam != null && !idParam.isEmpty()) {
           try {
               int id = Integer.parseInt(idParam);
               UserDTO userdto = userModel.getUserById(id);
               if(userdto!=null) {
                   resp.sendRedirect("http://localhost:8080/BaiTap4_Payara-1.0-SNAPSHOT/api/users/" + id);
                  return;
               }else{
                   req.setAttribute("error", "Không tìm thấy user");
               }
           } catch (Exception e) {
               req.setAttribute("error", "Không thể tìm kiếm sản phẩm");
           }

        } listUsers(req, resp);
    }


    //hàm add
    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String facebook = req.getParameter("facebook");
        String bio = req.getParameter("bio");
        UserDTO userDTO = new UserDTO(firstName, lastName, username, password, email, facebook, bio);
        userModel.addUser(userDTO);
        resp.sendRedirect("http://localhost:8080/BaiTap4_Payara-1.0-SNAPSHOT/api/users");
    }

    //hàm xóa
    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if(idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                userModel.deleteUser(id);
            } catch (Exception e) {
                req.setAttribute("error", "Không thể xóa user");
            }
        }
        resp.sendRedirect("http://localhost:8080/BaiTap4_Payara-1.0-SNAPSHOT/api/users");
    }

    //hàm update
    // Hàm update
    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id"); // Lấy ID từ form
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam); // Chuyển đổi ID thành số nguyên
                UserDTO userDTO = userModel.getUserById(id); // Tìm user theo ID

                if (userDTO != null) {
                    // Lấy thông tin từ form
                    String firstName = req.getParameter("firstName");
                    String lastName = req.getParameter("lastName");
                    String username = req.getParameter("username");
                    String password = req.getParameter("password");
                    String email = req.getParameter("email");
                    String facebook = req.getParameter("facebook");
                    String bio = req.getParameter("bio");

                    // Cập nhật thông tin của user
                    userDTO.setFirstName(firstName);
                    userDTO.setLastName(lastName);
                    userDTO.setUsername(username);
                    userDTO.setPassword(password);
                    userDTO.setEmail(email);
                    userDTO.setFacebook(facebook);
                    userDTO.setBio(bio);

                    // Gọi phương thức update trong model để lưu lại thay đổi
                    userModel.updateUser(userDTO);

                    // Sau khi cập nhật thành công, chuyển hướng về danh sách user
                    resp.sendRedirect("http://localhost:8080/BaiTap4_Payara-1.0-SNAPSHOT/api/users/" + id);
                    return;
                } else {
                    req.setAttribute("error", "Không tìm thấy user với ID đã nhập.");
                }
            } catch (NumberFormatException e) {
                req.setAttribute("error", "ID không hợp lệ.");
            }
        } else {
            req.setAttribute("error", "ID không được để trống.");
        }

        // Sau khi xử lý lỗi, vẫn trả về danh sách user
        listUsers(req, resp);
    }

}
