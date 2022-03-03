package sn.minfinances;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sn.minfinances.dao.IRoles;
import sn.minfinances.dao.IUser;
import sn.minfinances.entities.Roles;
import sn.minfinances.entities.User;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EvalsecurrityApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EvalsecurrityApplication.class, args);
	}

	@Autowired
	private IRoles rolesdao;
	@Autowired
	private IUser userdao;

	@Override
	public void run(String... args) throws Exception {
		Roles roles = new Roles();
		roles.setNom("ROLE_ADMIN");
		//roles = rolesdao.save(roles);
		//System.out.println(roles.getId());
		//User 1
		User user = new User();
		user.setNom("Ndione");
		user.setPrenom("Moise");
		user.setEmail("mndione@minfinances.sn");
		user.setEtat(1);
		BCryptPasswordEncoder pwdcrypt = new BCryptPasswordEncoder();
		String pwd = pwdcrypt.encode("passer123#@");
		user.setPassword(pwd);
		List<Roles> listRoles = new ArrayList<>();
		listRoles.add(rolesdao.getById(1));
		user.setRoles(listRoles);
		//userdao.save(user);
		//User 2
		user = new User();
		user.setNom("Ndiaye");
		user.setPrenom("Tidjani");
		user.setEmail("tndiaye@minfinces.sn");
		user.setEtat(1);
		pwd = pwdcrypt.encode("passer321!@");
		user.setPassword(pwd);
		List<Roles> listRoles2 = new ArrayList<>();
		listRoles2.add(rolesdao.getById(1));
		listRoles2.add(rolesdao.getById(2));
		user.setRoles(listRoles2);
		//user = userdao.save(user);
		//System.out.println(user.getId());
	}
}
