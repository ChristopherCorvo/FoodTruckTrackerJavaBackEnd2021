package com.lambdaschool.foundation;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.lambdaschool.foundation.models.*;
import com.lambdaschool.foundation.models.MenuItem;
import com.lambdaschool.foundation.services.RoleService;
import com.lambdaschool.foundation.services.TruckService;
import com.lambdaschool.foundation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.Locale;

/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */

@Transactional
@ConditionalOnProperty(
    prefix = "command.line.runner",
    value = "enabled",
    havingValue = "true",
    matchIfMissing = true
)
@Component
public class SeedData
    implements CommandLineRunner
{
    /**
     * Connects the Role Service to this process
     */
    @Autowired
    RoleService roleService;

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    @Autowired
    TruckService truckservice;

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args) throws
                                   Exception
    {
        // ----- User data --------
        userService.deleteAll();
        roleService.deleteAll();
        truckservice.deleteAllTrucks();

        Role r1 = new Role("admin");
        Role r2 = new Role("diner");
        Role r3 = new Role("truckoperator");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);



        // -------- Truck Seed Data ---------


            Truck t1 = new Truck("TruckName1",
                "imageURL1",
                "Italian",
                "100001",
                "100002",
                "12:01pm",
                5);
            MenuItem testMenuItem1 = new MenuItem("Mac and Cheese",
                "cheese and pasta",
                10,
                5,
                t1);
            testMenuItem1.getMenuItemPhotos()
                .add(new MenuItemPhoto("PhotoURL1",
                    testMenuItem1)); // repeat this for every truck object
            testMenuItem1.getMenuitemratings()
                .add(new MenuItemRating(5,
                    testMenuItem1));
            t1.getMenuItems()
                .add(testMenuItem1);

            MenuItem testMenuItem2 = new MenuItem("Chef Salad",
                "Salad with Chicken",
                15,
                5,
                t1);

            testMenuItem2.getMenuItemPhotos()
                .add(new MenuItemPhoto("PhotoURL2",
                    testMenuItem2));
            testMenuItem2.getMenuitemratings()
                .add(new MenuItemRating(2,
                    testMenuItem2));
            t1.getMenuItems()
                .add(testMenuItem2);

            MenuItem testMenuItem3 = new MenuItem("Clams Alfredo",
                "pasta and clams in a cheese sauce",
                25,
                5,
                t1);

            testMenuItem3.getMenuItemPhotos()
                .add(new MenuItemPhoto("PhotoURL3",
                    testMenuItem3));
            testMenuItem3.getMenuitemratings()
                .add(new MenuItemRating(1,
                    testMenuItem3));
            t1.getMenuItems()
                .add(testMenuItem3);

            t1 = truckservice.save(t1);


            Truck t2 = new Truck("TruckName2",
                "imageURL2",
                "BBQ",
                "100001",
                "100002",
                "12:01pm",
                5);

            MenuItem testMenuItem4 = new MenuItem("BBQ Chicken",
                "Slow cooked over fire",
                15,
                5,
                t2);

            testMenuItem4.getMenuItemPhotos()
                .add(new MenuItemPhoto("PhotoURL4",
                    testMenuItem4));
            testMenuItem4.getMenuitemratings()
                .add(new MenuItemRating(5,
                    testMenuItem4));
            t2.getMenuItems()
                .add(testMenuItem4);

            MenuItem testMenuItem5 = new MenuItem("BBQ Ribs",
                "slow cooked ribs",
                25,
                5,
                t2);

            testMenuItem5.getMenuItemPhotos()
                .add(new MenuItemPhoto("PhotoURL5",
                    testMenuItem5));
            testMenuItem5.getMenuitemratings()
                .add(new MenuItemRating(2,
                    testMenuItem5));
            t2.getMenuItems()
                .add(testMenuItem5);
            t2 = truckservice.save(t2);



            Truck t3 = new Truck("TruckName3",
                "imageURL3",
                "Mexican",
                "10001",
                "10002",
                "8:00pm",
                5);

            MenuItem testMenuItem6 = new MenuItem("Cheese Taco",
                "Taco with melted cheddar cheese",
                12,
                5,
                t3);

            testMenuItem6.getMenuItemPhotos()
                .add(new MenuItemPhoto("PhotoURL6",
                    testMenuItem6));
            testMenuItem6.getMenuitemratings()
                .add(new MenuItemRating(5,
                    testMenuItem6));
            t3.getMenuItems()
                .add(testMenuItem6);

            MenuItem testMenuItem7 = new MenuItem("Bean Burrito",
                "Burrito filled with cheese and black beans",
                13,
                5,
                t3);

            testMenuItem7.getMenuItemPhotos()
                .add(new MenuItemPhoto("PhotoURL7",
                    testMenuItem7));
            testMenuItem7.getMenuitemratings()
                .add(new MenuItemRating(2,
                    testMenuItem7));
            t3.getMenuItems()
                .add(testMenuItem7);

            t3 = truckservice.save(t3);


//        // admin
        User u1 = new User("admin",
            "password",
            "admin@lambdaschool.local",
            "0",
            "0");
        u1.getRoles()
            .add(new UserRoles(u1,
                r1));
        u1.getRoles()
            .add(new UserRoles(u1,
                r2));
        u1.getRoles()
            .add(new UserRoles(u1,
                r3));
        u1.getUseremails()
            .add(new Useremail(u1,
                "admin@email.local"));
        u1.getUseremails()
            .add(new Useremail(u1,
                "admin@mymail.local"));
        u1.getMytrucklist()
            .add(new MyTruckList(u1, t3));


        userService.save(u1);

        // u2 will have the role = "diner"
        User u2 = new User("TestDiner",
            "password",
            "cinnamon@lambdaschool.local",
            "0",
            "0");
        u2.getRoles()
            .add(new UserRoles(u2,
                r2));
        u2.getUseremails()
            .add(new Useremail(u2,
                "cinnamon@mymail.local"));
        u2.getUseremails()
            .add(new Useremail(u2,
                "hops@mymail.local"));
        u2.getUseremails()
            .add(new Useremail(u2,
                "bunny@email.local"));
        u2.getMytrucklist()
            .add(new MyTruckList(u2, t1));
        u2.getMytrucklist()
            .add(new MyTruckList(u2, t2));
        u2.getMytrucklist()
            .add(new MyTruckList(u2, t3));

        userService.save(u2);

        // U3 has the role of "TruckOperator"
        User u3 = new User("TruckOperator",
            "TruckOperator",
            "barnbarn@lambdaschool.local",
            "0",
            "0");
        u3.getRoles()
            .add(new UserRoles(u3,
                r3));
        u3.getUseremails()
            .add(new Useremail(u3,
                "barnbarn@email.local"));
        u3.getMytrucklist()
            .add(new MyTruckList(u3, t1));
        u3.getMytrucklist()
            .add(new MyTruckList(u3, t2));
        u3.getMytrucklist()
            .add(new MyTruckList(u3, t3));

        userService.save(u3);

        User u4 = new User("puttat",
            "password",
            "puttat@school.lambda",
            "0",
            "0");
        u4.getRoles()
            .add(new UserRoles(u4,
                r3));

        userService.save(u4);


        User u5 = new User("misskitty",
            "password",
            "misskitty@school.lambda",
            "0",
            "0");
        u5.getRoles()
            .add(new UserRoles(u5,
                r2));
        userService.save(u5);

        if (false)
        {
            // using JavaFaker create a bunch of regular users
            // https://www.baeldung.com/java-faker
            // https://www.baeldung.com/regular-expressions-java

            FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"),
                new RandomService());
            Faker nameFaker = new Faker(new Locale("en-US"));

            for (int i = 0; i < 25; i++)
            {
                new User();
                User fakeUser;

                fakeUser = new User(nameFaker.name()
                    .username(),
                    "password",
                    nameFaker.internet()
                        .emailAddress(),
                    nameFaker.number()
                        .toString(),
                    nameFaker.number()
                        .toString());
                fakeUser.getRoles()
                    .add(new UserRoles(fakeUser,
                        r2));
                fakeUser.getUseremails()
                    .add(new Useremail(fakeUser,
                        fakeValuesService.bothify("????##@gmail.com")));
                userService.save(fakeUser);
            }
        }
    }
}

