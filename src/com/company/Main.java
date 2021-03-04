package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    //                       тип защиты Босса, способ защиты от ударов Героев
    //                                       кол-во жизней Героев
    public static int[] heroesHealth = {260, 270, 250, 500, 285, 200, 250, 270};
    //                                      колич-во един. отнимающиеся от жизни Босса от удара Героев
    public static int[] heroesDamage = {20, 30, 20, 0, 25, 24, 20, 35};
    //                                              типы атаки Героев, которые их отличают
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Gambit", "Lucky", "Berserk", "Thor"};
    public static int roundNumber = 0;


    public static void main(String[] args) {
//        мы распечатываем начальную статистику перед запуском
        printStatistics();
//        тут ! - знак не, пока игра не закончилась, т.к кто-то жив
        while (!isGameFinished()) {
            round();
        }
    }

    //    этот метод будет вызывать те, действия кот-ые будт происходить в раунде
    public static void round() {

//      мы тут вызываем методы Раунда по номерам и инклиментирует раунд -
//      т.е раунд каждый раз будет на раунд увеличиваться
        roundNumber++;
        System.out.println("ROUNd №" + roundNumber);
        bossHits();
        heroesHit();
        printStatistics();
        berserk();
        luckyHeroes();
        gambitHit();
        medHill();
    }




    public static boolean isGameFinished() {

        //    здесь проверяем жив ли Босс и Герои и прдолжится ли игра,
        //    послдений return false - работет, если кто-то из них жив, то тогда игра продолжится
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

//      мы как пессимисты предполагаем, что все Герои мертвы, написали логику их смерти
        boolean allHeroesDead = true;
        for (int j : heroesHealth) {
            if (j >= 0) {
                allHeroesDead = false;
//              break - можно убрать, если мы хоитм всех героев проверить на жизнь, а так он здесь т.к нам надо по одному проверять
                break;
            }
        }

//        тут мы развеиваем наше предположение, что все Герои мертвы, есди да - Босс выиграл
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println("______________________________");
        System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]");

        //  int i = 0 = это герой Physical,тюк она на нулевом индексе и цикл потом проходит повсем индексам, т.е по массиву
        //  и мы написали ленгз, чтобы цикл проходил по всему массиву
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " [" + heroesDamage[i] + "]");
        }
    }

    public static void bossHits() {
        Random random = new Random();
        int a = random.nextInt(5);

        for (int i = 0; i < heroesHealth.length; i++) {
            if (a == 2) {
                System.out.println("Тор оглушил Босса!");
                break;
            }
            if (heroesHealth[i] > 0 && heroesHealth[4] == 0) {
                heroesHealth[i] -= bossDamage;

            }
            if (heroesHealth[i] > 0 && heroesHealth[4] > 0) {
                heroesHealth[i] -= 40;
            } else {
                heroesHealth[i] = 0;
//                    heroesHealth[i] = heroesHealth[i] - bossDamage;
            }
        }
    }

    public static void heroesHit() {
        for (int j : heroesDamage) {
            if (bossHealth > 0) {
                bossHealth -= j;
            } else {
                bossHealth = 0;
            }
        }
    }

    public static void berserk() {
        Random randomDamage = new Random();
        int getRandomDamage = randomDamage.nextInt(40);
        if (heroesHealth[6] > 0 && bossHealth > 0) {
            heroesHealth[6] = heroesHealth[6] - getRandomDamage;
            heroesDamage[6] += getRandomDamage;
            bossHealth -= getRandomDamage;
            System.out.println("berserk получил урон: " + getRandomDamage);
            System.out.println("berserk увеличил урон на: " + getRandomDamage);
            System.out.println("Данное время урон Berserka составляет: " + heroesDamage[2]);
        }
        if (heroesHealth[6] < 0) {
            heroesHealth[6] = 0;
        }
    }

    public static void gambitHit() {
        if (heroesHealth[4] > 0) {
            heroesHealth[4] -= bossDamage;
            System.out.println("Gambit забирает 1/5 часть урона босса по всем героям и минусует у себя жизнь ");
            if (heroesHealth[4] < 0) {
                heroesHealth[4] = 0;
            }
        }
    }

    public static void medHill() {
        for (int i = 0; i < heroesHealth.length; i++) {
            int random = (int) (Math.random() * 100);
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && heroesHealth[1] > 0 && !heroesAttackType[i].equals(heroesAttackType[1])) {
                heroesHealth[i] = heroesHealth[i] + random;
                System.out.println("Медик лечит :  " + heroesAttackType[i] + " на: " + random);
                break;

            }
        }
    }
    public static void luckyHeroes() {
        Random randomLucky = new Random();
        int randomMiss = randomLucky.nextInt(5);
        if (heroesHealth[5] > 0 && bossHealth > 0) {
            if (randomMiss == 1 && heroesHealth[4] > 0) {
                heroesHealth[5] = heroesHealth[5] + 40;
                System.out.println("lucky увернулся  от босса ");
            } else if (randomMiss == 1 && heroesHealth[4] < 0) {
                heroesHealth[5] = heroesHealth[5] + bossDamage;
            }
        }
    }
}

