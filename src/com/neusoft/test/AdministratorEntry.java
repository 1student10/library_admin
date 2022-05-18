package com.neusoft.test;

import com.neusoft.dao.impl.AdministratorDaoImpl;
import com.neusoft.dao.impl.BookDaoImpl;
import com.neusoft.dao.impl.RecordDaoImpl;
import com.neusoft.dao.impl.UserDaoImpl;
import com.neusoft.entity.Administrator;
import com.neusoft.entity.Book;
import com.neusoft.entity.User;

import java.util.ArrayList;
import java.util.Scanner;

public class AdministratorEntry {
    public static void main(String[] args) throws Exception {
        boolean flag1 = false; // 判断用户名和密码是否正确
        boolean flag2 = true; // 判断是否回到主菜单

        ArrayList<Administrator> listAdministrator = new ArrayList();// 存放管理员
        ArrayList<Book> listBook = new ArrayList();// 存放书

        // 实现方法的对象
        BookDaoImpl bdi = new BookDaoImpl();
        UserDaoImpl udi = new UserDaoImpl();
        AdministratorDaoImpl adi = new AdministratorDaoImpl();
        RecordDaoImpl rdi = new RecordDaoImpl();

        Administrator administrator = new Administrator();// 新建管理员对象

        Scanner sc = new Scanner(System.in);
        do {
            flag2 = false;// 重设flag2
            System.out.println("=========欢迎来到图书管理系统---管理员登录界面=========");
            System.out.println("====*功能选择表*====");
            System.out.println("1.登录\t2退出系统");
            System.out.println("请输入功能的序号:");
            int num = sc.nextInt(); // 获取操作指令
            if (num > 2) { // 判断输入是否为1到2的数字
                System.out.println("请输入1到2的数字！");
                flag2 = true;

            }

            switch (num){
                case 1:
                    do {  //管理员名或者密码输入错误返回此处
                        System.out.print("管理员名：");
                        String administratorname = sc.next();
                        System.out.print("管理员密码：");
                        String administratorpassword = sc.next();
                        administrator.setAccount(administratorname); // 设置管理员名和管理员密码
                        administrator.setPassword(administratorpassword);
                        listAdministrator = (ArrayList<Administrator>) adi.administratorQuery();
                        for (Administrator administrator1 : listAdministrator) {

                            if ((administrator1.getAccount()).equals(administrator.getAccount()) // 判断数据库中是否存在输入的管理员名和密码
                                    && (administrator1.getPassword()).equals(administrator.getPassword())) {
                                System.out.println("登录成功！");
                                System.out.println("当前管理员id为：" + administrator1.getId());
                                flag1 = true;
                                break;
                            }

                        }
                        if (!flag1) {          // 如果管理名或者密码输入错误
                            System.out.println("管理名或者管理密码输入错误！请重新输入");

                        }
                    } while (!flag1);   //判定条件
                    while (flag1) { // 如果登录成功
                        System.out.println("====*功能选择表*====");
                        System.out.println("1.添加用户\t2.删除用户\t3.添加书籍\t4.删除书籍\n5.修改管理员信息\t6.退出系统\t7.退出登录 ");
                        System.out.println("8.展示书库内的所有书籍\t9.检索图书(模糊查询)\n请输入功能的序号:");
                        int operate = sc.nextInt(); // 获取操作指令
                        switch (operate) {
                            case 1: // 添加用户
                                System.out.println("请输入要添加的用户信息：");
                                System.out.println("用户名：");
                                String userName = sc.next();
                                System.out.println("用户密码：");
                                String userPassword = sc.next();
                                User user1 = new User(userName, userPassword);
                                udi.userAdd(user1); // 用户添加方法
                                break;

                            case 2:// 删除用户
                                System.out.println("请输入要删除的用户名：");
                                String delUserName = sc.next();
                                udi.userDel(delUserName); // 用户删除方法
                                break;
                            case 3:// 添加书
                                System.out.println("请输入要添加书的信息：(书名，作者，价格)");
                                System.out.println("书名：");
                                String bookName = sc.next();
                                System.out.println("作者：");
                                String bookAuthor = sc.next();
                                System.out.println("价格：");
                                int bookPrice = sc.nextInt();
                                Book book1 = new Book(bookName, bookAuthor, bookPrice);
                                bdi.bookAdd(book1); // 添加书方法
                                break;
                            case 4:// 删除书
                                System.out.println("请输入要删除书的书名：");
                                String delBookName = sc.next();
                                bdi.bookDel(delBookName); // 删除书方法
                                break;
                            case 5:// 修改管理员信息
                                System.out.println("请输入你要修改的管理员的id：");
                                int updateAdministratorId = sc.nextInt();
                                System.out.println("请输入修改后的管理员用户名：");
                                String updateAdministratorName = sc.next();
                                System.out.println("请输入修改后的管理员密码：");
                                String updateAdministratorPassword = sc.next();
                                Administrator administrator2 = new Administrator(updateAdministratorId, updateAdministratorName,
                                        updateAdministratorPassword);
                                adi.administratorUpdate(administrator2);// 修改管理员信息方法
                                break;
                            case 6:
                                System.out.println("退出成功！期待您的下次使用");
                                System.exit(0);// 退出
                                break;
                            case 7: // 设置boolean条件，回到主菜单
                                flag1 = false;
                                flag2 = true;
                                break;

                            case 8://
                                listBook = (ArrayList<Book>) bdi.bookQuery();
                                System.out.println("书库内所有书籍如下：");
                                for (Book book : listBook) { // 遍历输出
                                    System.out.println("图书编号：" + book.getId() + "," + " 书名：" + "《" + book.getName() + "》" + ","
                                            + " 作者：" + book.getAuthor() + "," + "  价格：" + book.getPrice() + "元");
                                }
                                System.out.println("查询完毕\n");
                                flag2 = true; // 回到主菜单
                                break;

                            case 9:// 检索图书
                                System.out.println("请输入要查询图书的书名：");
                                String bookName1 = sc.next();
                                bdi.bookSelect(bookName1); // 检索图书方法
                                System.out.println("是否回到主菜单？   1.回到主菜单    2.退出系统");
                                int operation1 = sc.nextInt();
                                if (operation1 == 1) {
                                    flag2 = true;
                                } else {
                                    System.out.println("退出系统成功!");
                                    System.exit(0);
                                }
                                break;
                        }

                    }
                    break;

                case 2:
                    System.out.println("退出系统成功！期待您的下次使用");
                    System.exit(0);
                    break;
            }

        } while (flag2 == true);
    }
}
