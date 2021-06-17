package orchowski.tomasz.ecommercedemo.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.ecommercedemo.domain.Authority;
import orchowski.tomasz.ecommercedemo.domain.Item;
import orchowski.tomasz.ecommercedemo.domain.Role;
import orchowski.tomasz.ecommercedemo.domain.User;
import orchowski.tomasz.ecommercedemo.repository.AuthorityRepository;
import orchowski.tomasz.ecommercedemo.repository.ItemRepository;
import orchowski.tomasz.ecommercedemo.repository.RoleRepository;
import orchowski.tomasz.ecommercedemo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;



@Slf4j
@RequiredArgsConstructor
@Component
public class defaultLoader implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Override

    public void run(String... args) throws Exception {
        loadAccount();
        loadItem();
    }

    private void loadItem() {
        var p1 = Item.builder().description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").price(500.).stock(2).build();
        var p2=Item.builder().description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").price(100.).stock(0).build();
        var p3= Item.builder().description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").price(1000.).stock(1).build();
        var p4= Item.builder().description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").price(1000.).stock(1).build();
        var p5= Item.builder().description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").price(1000.).stock(1).build();
        var p6= Item.builder().description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").price(1000.).stock(1).build();
        var p7= Item.builder().description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").price(1000.).stock(1).build();
        var p8= Item.builder().description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").price(1000.).stock(1).build();
        var p9= Item.builder().description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").price(1000.).stock(1).build();
        var p10= Item.builder().description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").price(1000.).stock(1).build();
        var p11= Item.builder().description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").price(1000.).stock(1).build();
        var p12= Item.builder().description("Lorem ipsum dolor sit amet, dictas periculis argumentum ne ius, perfecto mandamus est te. Vivendo efficiendi mei cu, maiestatis vituperatoribus sit an. Vix in hinc eros disputationi. Ei mel soluta explicari, vim ut nulla dissentias. No modus illum quo, labore volumus lobortis cu sea, noluisse signiferumque te has. Vix te doming omnium repudiandae, ne cum dolore utroque voluptaria, ea ferri ullum quo. Eam eros senserit principes at, nec id sanctus facilisi. Cibo partiendo liberavisse eu quo, at laudem repudiandae pro. Sapientem definiebas per ei. Denique contentiones te per. Usu integre epicuri ea. Id sumo verterem pro. At laoreet fastidii percipit nam, pro velit indoctum at, te eam augue deleniti constituam. Ut pri integre postulant. Ea qui nibh idque, at iusto nobis usu. Ad cum vocibus fastidii partiendo, eum dolores recusabo ei.").price(1000.).stock(1).build();

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
        var storeItemCreate = authorityRepository.save(Authority.builder().permission("store.item.create").build());
        var storeItemRead = authorityRepository.save(Authority.builder().permission("store.item.read").build());
        var storeItemUpdate = authorityRepository.save(Authority.builder().permission("store.item.update").build());
        var storeItemDelete = authorityRepository.save(Authority.builder().permission("store.item.delete").build());



        var admin = roleRepository.save(Role.builder().
                authority(storeItemCreate).
                authority(storeItemRead).
                authority(storeItemUpdate).
                authority(storeItemDelete).
                name("ROLE_ADMIN").build());

        var customer = roleRepository.save(Role.builder().
            authority(storeItemRead).
            name("ROLE_CUSTOMER").
            build());

        userRepository.save(User.builder().
                role(admin).
                username("admin").
                password(passwordEncoder.encode("admin")).
                email("admin@admin.com").
                build()
        );


        userRepository.save(User.builder().
                role(customer).
                username("user").
                password(passwordEncoder.encode("user")).
                email("user@user.com").
                build()
        );

    }


}
