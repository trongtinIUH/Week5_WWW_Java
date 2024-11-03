<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
    <style>
        body {
            background-color: antiquewhite;
            background-position: center;
            font-family: Arial, sans-serif;
            display: flex;
            flex-direction: column;
            align-items: flex-end;
            margin: 0;
        }

        form {
            background-color: rgba(19, 19, 19, 0.8); /* Đặt nền form trong suốt */
            padding: 20px;
            border-radius: 8px;
            width: 400px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            color: #ffffff;
            margin-bottom: 20px;
        }

        h2 {
            text-align: center;
            color: #ffffff;
        }

        .inline-fields {
            display: flex;
            justify-content: space-between;
            gap: 10px;
        }

        label {
            display: block;
            margin-top: 10px;
            font-size: 14px;
        }

        input[type="text"], input[type="email"], input[type="password"], textarea {
            width: calc(100% - 10px);
            padding: 8px;
            margin-top: 5px;
            border: none;
            border-radius: 4px;
            font-size: 14px;
        }

        input[type="submit"] {
            background-color: #333;
            color: #fff;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 15px;
            width: 100%;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #555;
        }

        .required::after {
            content: '*';
            color: red;
        }

        .button {
            background-color: #333;
            color: #fff;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 150px;
            font-size: 16px;
            margin-top: 10px;
        }

        .button:hover {
            background-color: #555;
        }

        .buttons-container {
            display: flex;
            justify-content: space-between;
            width: 100%;
            max-width: 600px;
        }

        .buttons-container form {
            width: 200px;
        }

        .hinh{
            position: absolute;
            top: 0;
            left: 0;
            width: 100px;
            height: 100px;
        }
    </style>
</head>
<body>
<div class="hinh">
    <img src="https://1.bp.blogspot.com/-KiiVgbOcA2s/Xp3JoQri1yI/AAAAAAAAh4s/d1nZKsMBQNUFrw3g7VCC-IqTH65rMn9EQCLcBGAsYHQ/s1600/Hinh-nen-blackpink%2B%252819%2529.jpg" alt="" style="height: 600px; position: absolute; left: 350px ">

</div>

<!-- Form đăng ký user mới -->
<form method="post" action="controller">
    <input type="hidden" name="action" value="add_user">
    <h2>Register User</h2>
    <div class="inline-fields">
        <div>
            <label for="fname" class="required">First Name</label>
            <input type="text" id="fname" name="firstName" required>
        </div>
        <div>
            <label for="lname" class="required">Last Name</label>
            <input type="text" id="lname" name="lastName" required>
        </div>
    </div>
    <label for="username" class="required">Username</label>
    <input type="text" id="username" name="username" required>
    <label for="email" class="required">E-mail</label>
    <input type="email" id="email" name="email" required>
    <label for="password" class="required">Password</label>
    <input type="password" id="password" name="password" required>
    <label for="facebook">Facebook</label>
    <input type="text" id="facebook" name="facebook" placeholder="Enter your Facebook profile URL.">
    <label for="bio">Short Bio</label>
    <textarea id="bio" name="bio" placeholder="Chia sẽ trải nghiệm của bạn."></textarea>
    <input type="submit" value="Submit">
</form>

<!-- Các nút Delete, Find, Update, List Users -->
<div class="buttons-container">
    <!-- Xóa user -->
    <form method="post" action="controller">
        <input type="hidden" name="action" value="delete_user">
        <h3>Delete User</h3>
        <label for="deleteId">User ID</label>
        <input type="text" id="deleteId" name="id" required>
        <input type="submit" value="Delete" class="button">
    </form>

    <!-- Tìm user -->
    <form method="get" action="controller">
        <input type="hidden" name="action" value="find_user">
        <h3>Find User</h3>
        <label for="findId">User ID</label>
        <input type="text" id="findId" name="id" required>
        <input type="submit" value="Find" class="button">
    </form>

    <!-- Cập nhật user -->
    <form method="post" action="controller">
        <input type="hidden" name="action" value="update_user">

        <h2>Update User</h2>

        <!-- Nhập ID của user cần cập nhật -->
        <label for="id_update" class="required">User ID</label>
        <input type="text" id="id_update" name="id" required>

        <div class="inline-fields">
            <div>
                <label for="fname_update" class="required">First Name</label>
                <input type="text" id="fname_update" name="firstName" required>
            </div>
            <div>
                <label for="lname_update" class="required">Last Name</label>
                <input type="text" id="lname_update" name="lastName" required>
            </div>
        </div>

        <label for="username_update" class="required">Username</label>
        <input type="text" id="username_update" name="username" required>

        <label for="email_update" class="required">E-mail</label>
        <input type="email" id="email_update" name="email" required>

        <label for="password_update" class="required">Password</label>
        <input type="password" id="password_update" name="password" required>

        <label for="facebook_update">Facebook</label>
        <input type="text" id="facebook_update" name="facebook">

        <label for="bio_update">Short Bio</label>
        <textarea id="bio_update" name="bio"></textarea>

        <input type="submit" value="Update">
    </form>

    <!-- Danh sách user -->
    <form method="get" action="controller">
        <input type="hidden" name="action" value="list_users">
        <h3>List Users</h3>
        <input type="submit" value="List Users" class="button">
    </form>
</div>

</body>
</html>
