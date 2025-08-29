import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class BonusServiceTest {

    // !!!!Первые два теста это те, которые были из условия задания!!!!

    @Test
    void shouldCalculateForRegisteredAndUnderLimit() {
        BonusService service = new BonusService();

        // 1 первый тест (не мой)
        // подготавливаем данные: ЗАРЕГИСТРИРОВАННЫЙ пользователь, малая сумма
        long amount = 1_000;
        boolean registered = true;
        long expected = 30;  // 1_000 × 3% = 30

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateForRegisteredAndOverLimit() {
        BonusService service = new BonusService();

        // 2 второй тест
        // подготавливаем данные: НЕЗАРЕГИСТРИРОВАННЫЙ пользователь, большая сумма
        long amount = 1_000_000;
        boolean registered = true;
        long expected = 500;  // 1_000_000 × 3% = 30_000 - ограничение 500

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateForUnregisteredAndUnderLimit() {
        BonusService service = new BonusService();

        // 3 третий тест (здесь мой)
        // подготавливаем данные: незарегистрированный пользователь, малая сумма
        long amount = 1_000;
        boolean registered = false;  // !!!!НЕзарегистрированный!!!!
        long expected = 10;          // 1_000 × 1% = 10 (НЕ 3%!)

        // вызываем целевой метод: проверяем что для НЕзарегистрированных работает 1%
        long actual = service.calculate(amount, registered);

        // производим проверку (убеждаемся что процент правильный, именно 1%):
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateForUnregisteredAndOverLimit() {
        BonusService service = new BonusService();

        // 4 четвёртый тест
        // подготавливаем данные: НЕзарегистрированный пользователь, большая сумма
        long amount = 100_000;
        boolean registered = false;  //  НЕзарегистрированный
        long expected = 500;         //  100_000 × 1% = 1_000 - ограничение 500

        // вызываем целевой метод: проверяем то, что лимит работает и для незарегистрированных
        long actual = service.calculate(amount, registered);

        // производим проверку (убеждаемся в том, что лимит применяется корректно):
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateForRegisteredWithZeroAmount() {
        BonusService service = new BonusService();

        // 5 пятый тест
        // подготавливаем данные: зарегистрированный пользователь, НУЛЕВАЯ сумма
        long amount = 0; //граничное значение
        boolean registered = true;
        long expected = 0; //0*3%=0

        // вызываем целевой метод: проверяем обработку !нулевой! суммы
        long actual = service.calculate(amount, registered);

        // производим проверку (убеждаемся что программа не ломается на нулях):
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateForUnregisteredWithZeroAmount() {
        BonusService service = new BonusService();
        
        // 6 шестой тест
        // подготавливаем данные: незарегистрированный пользователь, НУЛЕВАЯ сумма
        long amount = 0;             //граничное значение
        boolean registered = false;
        long expected = 0;           //0*1%=0

        // вызываем целевой метод: проверяем обработку нулевой суммы для всех типов пользователей
        long actual = service.calculate(amount, registered);

        // производим проверку (полное покрытие граничных условий):
        Assertions.assertEquals(expected, actual);
    }
}