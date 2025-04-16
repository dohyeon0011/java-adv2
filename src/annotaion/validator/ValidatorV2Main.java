package annotaion.validator;

import static util.MyLogger.log;

// 애노테이션 검증기(Validator)를 이용해서 어떤 객체든지 간단하게 검증 가능.
public class ValidatorV2Main {

    public static void main(String[] args) {
        User user = new User("user1", 0);
        Team team = new Team("", 0);

        try {
            log("== user 검증 ==");
            Validator.validate(user);
        } catch (Exception e) {
            log(e);
        }

        try {
            log("== team 검증 ==");
            Validator.validate(team);
        } catch (Exception e) {
            log(e);
        }
    }
}
