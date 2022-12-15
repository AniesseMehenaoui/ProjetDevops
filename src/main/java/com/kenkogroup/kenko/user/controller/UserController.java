//package com.kenkogroup.kenko.user.controller;
//import com.kenkogroup.kenko.jwt.JwtController;
//import com.kenkogroup.kenko.jwt.JwtFilter;
//import com.kenkogroup.kenko.jwt.JwtUtils;
//import com.kenkogroup.kenko.user.entity.User;
//import com.kenkogroup.kenko.user.exception.UserNullException;
//import com.kenkogroup.kenko.user.repository.UserRepository;
////import com.kenkogroup.kenko.user.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.util.StringUtils;
//
//import org.springframework.web.bind.annotation.*;
//
//
//import javax.validation.Valid;
//import java.util.List;
//
//
//
//@RestController
//
//public class UserController {
//    /*@Autowired
//    private UserService service;*/
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    JwtController jwtController;
//
//    @Autowired
//    JwtUtils jwtUtils;
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/adduser")
//    public ResponseEntity addUser(@Valid @RequestBody  User userInfo){
//        User existingUser = userRepository.findOneByEmail(userInfo.getEmail());
//        if(existingUser!= null) {
//            return new ResponseEntity("User already existing", HttpStatus.BAD_REQUEST);
//        }
//
//       User userSaved =  saveUser(userInfo);
//        Authentication authentication = jwtController.logUser(userInfo.getEmail(),userInfo.getPassword());
//        String jwt = jwtUtils.generateToken(authentication);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
//        return new ResponseEntity(userSaved,HttpStatus.CREATED);
//
//    }
//    @GetMapping(value = "/isConnected")
//    public ResponseEntity getUSerConnected() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails) {
//            return new ResponseEntity(((UserDetails) principal).getUsername(), HttpStatus.OK);
//        }
//        return new ResponseEntity("User is not connected", HttpStatus.FORBIDDEN);
//    }
//    private User saveUser(User userInfo) {
//        User user = new User();
//        user.setEmail(userInfo.getEmail());
//        user.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
//        user.setLastName(StringUtils.capitalize(userInfo.getLastName()));
//        user.setFirstName(StringUtils.capitalize(userInfo.getFirstName()));
//        userRepository.save(user);
//        return user;
//    }
//
//
//    /*@ResponseStatus(HttpStatus.OK)
//    @PostMapping("/user/addusers")
//    public List<User> addUsers(@RequestBody List<User> users){ return service.saveUsers(users); }
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/user/id/{id}")
//    public User findUserById(@PathVariable int id){
//        return service.getUserbyId(id);
//    }
//
//   /* @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/users/firstname/{firstname}")
//    public List<User> findUserByFirstname(@PathVariable String firstname){
//        return service.getUserByFirstname(firstname);
//    }*/
//
//    /*@ResponseStatus(HttpStatus.OK)
//    @GetMapping("/users/lastname/{lastname}")
//    public List<User> findUserByLastname(@PathVariable String lastname){
//        return service.getUserByLastname(lastname);
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/users/age/{age}")
//    public List<User> findUserByAge(@PathVariable int age){
//        return service.getUserByAge(age);
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/users/size/{size}")
//    public List<User> findUserBySize(@PathVariable float size){
//        return service.getUserBySize(size);
//    }
//
//    @GetMapping("/users/weight/{weight}")
//    public List<User> findUserByWeight(@PathVariable float weight){
//        return service.getUserByWeight(weight);
//    }
//
//    /*@ResponseStatus(HttpStatus.OK)
//    @GetMapping("/users/sexe/{sexe}")
//    public List<User> findUserBySexe(@PathVariable User.Sexe sexe){
//        return service.getUserBySexe(sexe);
//    }*/
//
//    /*@ResponseStatus(HttpStatus.OK)
//    @GetMapping("/users/userCategory/{userCategory}")
//    public List<User> findUserByUserCategory(@PathVariable User.UserCategory userCategory){
//        return service.getUserByUserCat(userCategory);
//    }*/
//
//    /*@ResponseStatus(HttpStatus.OK)
//    @GetMapping("/users")
//    public List<User> findAllUsers(){
//        return service.getUsers();
//    }
///*
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    @DeleteMapping("/user/delete/{id}")
//    public String deleteUser(@PathVariable int id){
//        return service.deleteUserbyId(id);
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @PutMapping("/user/update/{id}")
//    public User updateUser(@RequestBody User user,@PathVariable int id) throws UserNullException {
//        return service.updateUserbyId(user,id);
//    }
//
//    @RequestMapping("/")
//    public String hello()
//    {
//        return "Hello javaTpoint";
//    }
//
//   /* @PostMapping("/user/recipe")
//    public User addPersonalizedRecipe(@RequestBody Recipe recipe,@PathVariable int id){
//        return service.saveRecipe(Recipe,id);*/
//
//}
