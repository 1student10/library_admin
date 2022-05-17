package com.neusoft.test;

import com.neusoft.dao.impl.AdministratorDaoImpl;
import com.neusoft.dao.impl.BookDaoImpl;
import com.neusoft.dao.impl.RecordDaoImpl;
import com.neusoft.dao.impl.UserDaoImpl;
import com.neusoft.entity.Book;
import com.neusoft.entity.Record;
import com.neusoft.entity.User;

import java.util.ArrayList;
import java.util.Scanner;

public class UserEntry {

    public static void main(String[] args) throws Exception {
        boolean flag1 = false; // 判断用户名和密码是否正确
        boolean flag2 = true; // 判断是否回到主菜单

        // 实现方法的对象
        BookDaoImpl bdi = new BookDaoImpl();
        UserDaoImpl udi = new UserDaoImpl();
        AdministratorDaoImpl adi = new AdministratorDaoImpl();
        RecordDaoImpl rdi = new RecordDaoImpl();

        ArrayList<User> listUser = new ArrayList();// 存放用户
        ArrayList<Book> listBook = new ArrayList();// 存放书
        User user = new User();// 新建用户对象

        Scanner sc = new Scanner(System.in);
        do { // 如果flag2=true就回到主菜单
            flag2 = false;// 重设flag2
            System.out.println("=========欢迎来到图书管理系统---用户登录界面=========");
            System.out.println("====*功能选择表*====");
            System.out.println("1.登录\t2.注册用户\t3.退出系统");
            System.out.println("请输入功能的序号:");
            int num = sc.nextInt(); // 获取操作指令
            if (num > 3) { // 判断输入是否为1到6的数字
                System.out.println("请输入1到3的数字！");
                flag2 = true;

            }

        switch (num) {
            case 1://用户登录
                do {//用户员名或者密码输入错误返回此处
                    System.out.print("用户名：");
                    String username = sc.next();
                    System.out.print("用户密码：");
                    String userpassword = sc.next();
                    user.setAccount(username); // 设置用户名和密码
                    user.setPassword(userpassword);
                    listUser = (ArrayList<User>) udi.userQuery();
                    for (User user1 : listUser) {
                        if ((user1.getAccount()).equals(user.getAccount()) // 判断数据库中是否存在输入的用户名和密码
                                && (user1.getPassword()).equals(user.getPassword())) {
                            System.out.println("登录成功！");
                            System.out.println("当前用户id为：" + user1.getId());
                            flag1 = true;
                            break;
                        }
                    }
                    if (!flag1) { // 如果用户名或者密码输入错误
                        System.out.println("用户名或者密码输入错误！请重新输入");

                    }

                } while (!flag1);    //判定条件
                while (flag1) { // 如果成功登录
                    System.out.println("====*功能选择表*====");
                    System.out.println("\n1.展示书库内的所有书籍\t2.检索图书(模糊查询)\t3.借书\t4.还书\n5.修改用户信息\t6.退出系统\t7.退出登录"
                    +"\n请输入您需要的操作：");
                    int operation = sc.nextInt();
                    switch (operation) {
                        case 1://
                            listBook = (ArrayList<Book>) bdi.bookQuery();
                            System.out.println("书库内所有书籍如下：");
                            for (Book book1 : listBook) { // 遍历输出
                                System.out.println("图书编号：" + book1.getId() + "," + " 书名：" + "《" + book1.getName() + "》" + ","
                                        + " 作者：" + book1.getAuthor() + "," + "  价格：" + book1.getPrice() + "元");
                            }
                            System.out.println("查询完毕\n");
                            flag2 = true; // 回到主菜单
                            break;

                        case 2:// 检索图书
                            System.out.println("请输入要查询图书的书名：");
                            String bookName = sc.next();
                            bdi.bookSelect(bookName); // 检索图书方法
                            System.out.println("是否回到主菜单？   1.回到主菜单    2.退出系统");
                            int operation1 = sc.nextInt();
                            if (operation1 == 1) {
                                flag2 = true;
                            } else {
                                System.out.println("退出系统成功!");
                                System.exit(0);
                            }
                            break;

                        case 3:// 借书
                            System.out.println("请输入要借书的书名：（注意：不需要书名号《》）");
                            String bookName1 = sc.next();
                            System.out.println("借书者：");
                            String personName = sc.next();
                            Record record1 = new Record(bookName1, personName); // 书名和借书者
                            rdi.recordAdd(record1);// 借书方法
                            break;

                        case 4:// 还书
                            System.out.println("请输入要还书的图书书名：（注意：不需要书名号《》）");
                            String delBookName = sc.next();
                            rdi.recordDel(delBookName);
                            break;

                        case 5:// 修改用户信息
                            System.out.println("请输入要修改账户的id");
                            int updateUserId = sc.nextInt();
                            System.out.println("请输入修改后的用户名");
                            String updateUserName = sc.next();
                            System.out.println("请输入修改后的密码");
                            String updateUserPassword = sc.next();
                            User user2 = new User(updateUserId, updateUserName, updateUserPassword);
                            udi.userUpdate(user2);
                            break;

                        case 6: // 退出系统
                            System.out.println("退出系统成功！期待您的下次使用");
                            System.exit(0);
                            break;

                        case 7:// 回到主菜单
                            flag1 = false;
                            flag2 = true;
                            break;
                    }
                }
                break;

            case 2: // 注册用户
                System.out.println("请输入要注册的用户信息：");
                System.out.println("用户名：");
                String userName = sc.next();
                System.out.println("用户密码：");
                String userPassword = sc.next();
                User user2 = new User(userName, userPassword);
                udi.userAdd(user2); // 用户注册
                flag2 = true; // 回到主菜单
                break;

            case 3:
                System.out.println("退出系统成功！期待您的下次使用");
                System.exit(0);
                break;
        }

        }while (flag2 == true);// 判断是否回到主菜单
    }

}
