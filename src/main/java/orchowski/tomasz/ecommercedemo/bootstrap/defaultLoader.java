package orchowski.tomasz.ecommercedemo.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.ecommercedemo.domain.*;
import orchowski.tomasz.ecommercedemo.domain.enums.OrderState;
import orchowski.tomasz.ecommercedemo.repository.*;
import orchowski.tomasz.ecommercedemo.services.DeliveryAddressService;
import orchowski.tomasz.ecommercedemo.services.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Component
public class defaultLoader implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final DeliveryAddressService deliveryAddressService;
    private final OrderService orderService;

    private Item p1;
    private Item p2;
    private Item p3;
    private Item p4;
    private Item p5;
    private Item p6;
    private Item p7;
    private Item p8;
    private Item p9;
    private Item p10;
    private Item p11;
    private Item p12;

    @Override
    public void run(String... args) throws Exception {
        loadAccount();
        loadItem();
    }

    private void loadItem() {
        p1 = Item.builder().
                name("item 1").
                description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").
                price(500.).
                isSell(true).
                sellPrice(490.).
                stock(2).
                build();
        p2 = Item.builder().
                name("item 2").
                description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").
                price(100.).
                isSell(true).
                sellPrice(80.).
                stock(0).
                build();
        p3 = Item.builder().
                name("item 3").
                description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").
                price(1000.).
                stock(1).
                build();
        p4 = Item.builder().
                name("item 3").
                description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").
                price(1000.).
                stock(10).
                build();
        p5 = Item.builder().
                name("item 4").
                description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").
                price(1000.).
                stock(0).
                build();
        p6 = Item.builder().
                name("item 5").
                description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").
                price(1000.).
                isSell(true).
                sellPrice(712.).
                stock(1).
                build();
        p7 = Item.builder().
                name("item 6").
                description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").
                price(511.).
                isSell(true).
                sellPrice(410.5).
                stock(0).
                build();
        p8 = Item.builder().
                name("item 7").
                description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").
                price(1000.).
                stock(1).
                build();
        p9 = Item.builder().
                name("item 8").
                description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").
                price(1000.).
                stock(1).
                build();
        p10 = Item.builder().
                name("item 9").
                description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").
                price(1000.).
                stock(1).
                build();
        p11 = Item.builder().
                name("item 10").
                description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").
                price(1000.).
                stock(1).
                build();
        p12 = Item.builder().
                name("item 11").
                description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").
                price(1000.).
                stock(1).
                build();

        itemRepository.save(p1);
        itemRepository.save(p2);
        itemRepository.save(p3);
        itemRepository.save(p4);
        itemRepository.save(p5);
        itemRepository.save(p6);
        itemRepository.save(p7);
        itemRepository.save(p8);
        itemRepository.save(p9);
        itemRepository.save(p10);
        itemRepository.save(p11);
        itemRepository.save(p12);

    }

    private void loadAccount() {
        //store
        var storeItemCreate = authorityRepository.save(Authority.builder().permission("store.item.create").build());
        var storeItemRead = authorityRepository.save(Authority.builder().permission("store.item.read").build());
        var storeItemUpdate = authorityRepository.save(Authority.builder().permission("store.item.update").build());
        var storeItemDelete = authorityRepository.save(Authority.builder().permission("store.item.delete").build());

        //admin page
        var adminPageUserEdit = authorityRepository.save(Authority.builder().permission("adminPage.user.edit").build());
        var adminPageUserShow = authorityRepository.save(Authority.builder().permission("adminPage.user.show").build());//TODO implement interface annotation


        //Roles
        var admin = roleRepository.save(Role.builder().
                authority(storeItemCreate).
                authority(storeItemRead).
                authority(storeItemUpdate).
                authority(storeItemDelete).
                authority(adminPageUserEdit).
                name("ROLE_ADMIN").build());

        var customer = roleRepository.save(Role.builder().
                authority(storeItemRead).
                name("ROLE_CUSTOMER").
                build());


        //Accounts
        //USER 1
        var u1 = userRepository.save(User.builder().
                role(admin).
                username("admin").
                password(passwordEncoder.encode("admin")).
                email("admin@admin.com").
                build()
        );

        deliveryAddressService.save(
                DeliveryAddress.builder().
                        user(u1).
                        zipCode("22-463").
                        city("Radecznica").
                        street("Zaburze").
                        homeNumber("111")
                        .phoneNumber("123 321 123").
                        build()
        );

        deliveryAddressService.save(
                DeliveryAddress.builder().
                        user(u1).
                        zipCode("30-063").
                        city("Krakow").
                        street("Aleja 3 maja").
                        homeNumber("99")
                        .phoneNumber("123 321 123").
                        build()
        );

        orderService.save(
                Order.builder().
                        user(u1).
                        orderState(OrderState.PLACED).
                        build()
        );
        //USER 1

        var u2 = userRepository.save(User.builder().
                role(customer).
                username("user").
                password(passwordEncoder.encode("user")).
                email("user@user.com").
                build()
        );

        var u3 = userRepository.save(User.builder().
                role(customer).
                username("user1").
                accountNonLocked(false).
                password(passwordEncoder.encode("user")).
                email("user@dad.com").
                build()
        );

        var u4 = userRepository.save(User.builder().
                role(customer).
                username("user2").
                password(passwordEncoder.encode("user")).
                email("udsaddser@user.com").
                build()
        );

        userRepository.save(User.builder().
                role(customer).
                username("user3").
                password(passwordEncoder.encode("user")).
                email("asdf@user.com").
                build()
        );

        userRepository.save(User.builder().
                role(customer).
                username("user4").
                password(passwordEncoder.encode("user")).
                email("usfgsdgser@user.com").
                build()
        );

        userRepository.save(User.builder().
                role(customer).
                username("user5").
                password(passwordEncoder.encode("user")).
                email("asdasda@user.com").
                build()
        );

        userRepository.save(User.builder().
                role(customer).
                username("user6").
                password(passwordEncoder.encode("user")).
                email("user6@user.com").
                build()
        );

        userRepository.save(User.builder().
                role(customer).
                username("user7").
                password(passwordEncoder.encode("user")).
                email("user7@user.com").
                build()
        );

        userRepository.save(User.builder().
                role(customer).
                username("user8").
                password(passwordEncoder.encode("user")).
                email("user8@user.com").
                build()
        );

        userRepository.save(User.builder().
                role(customer).
                username("user9").
                password(passwordEncoder.encode("user")).
                email("user9@user.com").
                build()
        );

        userRepository.save(User.builder().
                role(customer).
                username("user10").
                password(passwordEncoder.encode("user")).
                email("user10@user.com").
                build()
        );

        userRepository.save(User.builder().
                role(customer).
                username("user11").
                password(passwordEncoder.encode("user")).
                email("user11@user.com").
                build()
        );
    }


}
