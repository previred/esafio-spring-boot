package cl.nuevospa.taskmanagement.service;

import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
class UserServiceImplTest {
/**
    @Autowired
    private UserService userService;

    @MockBean
    private UserConverter userConverter;

    @MockBean
    private UserRepository userRepository;


    // validate that the user is created correctly when valid input is entered.
    @Test
    public void createUserWhenValidInput() {
        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .email("test@example.com")
                .password("Password123")
                .build();

        User user = new User();
        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .id(UUID.randomUUID())
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token("testToken")
                .isActive(true)
                .build();

        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userConverter.convertToEntity(any(UserRequestDTO.class))).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userConverter.convertToDTO(any(User.class))).thenReturn(userResponseDTO);

        UserResponseDTO result = userService.createUser(userRequestDTO);

        assertNotNull(result);
        assertEquals(userResponseDTO, result);
        verify(userRepository).save(any(User.class));
    }


    // validate that the expected exception is thrown when the email is already registered.
    @Test
    public void createUserWhenEmailAlreadyExists() {
        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .email("existing@example.com")
                .password("Password123")
                .build();

        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        assertThrows(EmailAlreadyRegisteredException.class, () -> {
            userService.createUser(userRequestDTO);
        });
    }


    // Validate to get all users
    @Test
    public void getAllUsers() {
        List<User> users = Arrays.asList(
                new User(),
                new User(),
                new User()
        );

        UUID fixedUUID = UUID.randomUUID();
        List<UserResponseDTO> userResponseDTOs = users.stream()
                .map(user -> UserResponseDTO.builder()
                        .id(fixedUUID)
                        .email("test@example.com")
                        .build())
                .collect(Collectors.toList());

        when(userRepository.findAll()).thenReturn(users);
        when(userConverter.convertToDTOList(users)).thenReturn(userResponseDTOs);

        List<UserResponseDTO> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(userResponseDTOs.size(), result.size());
        for (int i = 0; i < userResponseDTOs.size(); i++) {
            assertEquals(userResponseDTOs.get(i).getId(), result.get(i).getId());
            assertEquals(userResponseDTOs.get(i).getEmail(), result.get(i).getEmail());
        }

        verify(userRepository).findAll();
        verify(userConverter).convertToDTOList(users);
    }


    // Vadate that the expected exception is thrown when the password does not meet the expected format.
    @Test
    public void createUserWhenPasswordInvalid() {
        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .email("test@example.com")
                .password("123")
                .build();

        assertThrows(InvalidPasswordFormatException.class, () -> {
            userService.createUser(userRequestDTO);
        });
    }

   // Valdate that get user by id when user exists
    @Test
    public void getUserWhenUserExists() {
        UUID userId = UUID.randomUUID();
        User user = new User();
        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .id(userId)
                .email("test@example.com")
                .build();

        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));
        when(userConverter.convertToDTO(any(User.class))).thenReturn(userResponseDTO);

        UserResponseDTO result = userService.getUser(userId.toString());

        assertNotNull(result);
        assertEquals(userResponseDTO, result);
        verify(userRepository).findById(any(UUID.class));
    }

    // Validate that the expected exception is thrown when the user does not exist.
    @Test
    public void getUserWhenUserDoesNotExist() {
        UUID userId = UUID.randomUUID();

        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            userService.getUser(userId.toString());
        });

        assertEquals("Usuario no encontrado con ID: " + userId.toString() , thrown.getMessage());
        verify(userRepository).findById(any(UUID.class));
    }


    // Validate that the user is updated correctly when valid input is entered.
    @Test
    public void updateUserWhenValidInput() {
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);
        user.setPhones(new ArrayList<>());

        UserRequestDTO updateRequest = UserRequestDTO.builder()
                .email("updated@example.com")
                .password("UpdatedPassword123")
                .build();

        UserResponseDTO updatedResponse = UserResponseDTO.builder()
                .id(userId)
                .email("updated@example.com")
                .build();

        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userConverter.convertToDTO(any(User.class))).thenReturn(updatedResponse);

        UserResponseDTO result = userService.updateUser(userId.toString(), updateRequest);

        assertNotNull(result);
        assertEquals(updatedResponse, result);
        verify(userRepository).save(any(User.class));
    }


    // Validate that the expected exception is thrown when the user does not exist.
    @Test
    public void updateUserWhenUserDoesNotExist() {
        UUID userId = UUID.randomUUID();
        UserRequestDTO updateRequest = UserRequestDTO.builder()
                .email("updated@example.com")
                .password("UpdatedPassword123")
                .build();

        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            userService.updateUser(userId.toString(), updateRequest);
        });

        assertEquals("Usuario no encontrado con ID: " + userId.toString(), thrown.getMessage());
        verify(userRepository).findById(any(UUID.class));
    }


    // Validate that the expected exception is thrown when the password does not meet the expected format.
    @Test
    public void updateUserWhenInvalidPasswordFormat() {
        UUID userId = UUID.randomUUID();
        UserRequestDTO updateRequest = UserRequestDTO.builder()
                .email("updated@example.com")
                .password("123")
                .build();

        assertThrows(InvalidPasswordFormatException.class, () -> {
            userService.updateUser(userId.toString(), updateRequest);
        });
    }


    // Validate that the user is deleted correctly when valid input is entered.
    @Test
    public void deleteUserWhenUserExists() {
        UUID userId = UUID.randomUUID();
        User user = User.builder()
                .id(userId)
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        userService.deleteUser(userId.toString());

        verify(userRepository).findById(userId);
        verify(userRepository).delete(user);
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> userService.getUser(userId.toString()));
        assertEquals("Usuario no encontrado con ID: " + userId, thrown.getMessage());
    }


    // Validate that the expected exception is thrown when the user does not exist.
    @Test
    public void deleteUserWhenUserDoesNotExist() {
        UUID userId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());


        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(userId.toString()));
        assertEquals("Usuario no encontrado con ID: " + userId, thrown.getMessage());
        verify(userRepository, never()).delete(any(User.class));
    }

 **/
}
