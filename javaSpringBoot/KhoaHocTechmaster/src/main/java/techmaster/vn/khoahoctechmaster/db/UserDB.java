package techmaster.vn.khoahoctechmaster.db;

import techmaster.vn.khoahoctechmaster.model.Course;
import techmaster.vn.khoahoctechmaster.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
    public static List<User> courseList = new ArrayList<>(
            List.of(
                    new User(1
                            , "Đức Thịnh"
                            , "thinh@techmaster.vn"
                            , "0987273764"
                            , "https://media.techmaster.vn/api/static/c2m5ou451cob24f6skeg/c3IwVOU2"),
                    new User(2
                            , "Bùi Hiên"
                            , "email2@mail.com"
                            , "0123 456 789"
                            , "https://media.techmaster.vn/api/static/crop/c61mglc51co49felaphg"),
                    new User(3
                            , "Nguyễn Đức Hoàng"
                            , "email3@mail.com"
                            , "0123 456 789"
                            , "https://media.techmaster.vn/api/img/users/3979.png"),
                    new User(4
                            , "Chu Tiến Đạt"
                            , "email4@mail.com"
                            , "0123 456 789"
                            , "https://techmaster.vn/resources/image/anonymous-user.jpg"),
                    new User(5
                            , "Hoàng Văn Công"
                            , "email5@mail.com"
                            , "0123 456 789"
                            , "https://media.techmaster.vn/api/static/crop/bu93tm451co5836g4lag"),
                    new User(6
                            , "Ngọc Lục"
                            , "email6@mail.com"
                            , "0123 456 789"
                            , "https://media.techmaster.vn/api/static/crop/c10b0uk51coag9451f10")
            )
    );
}
