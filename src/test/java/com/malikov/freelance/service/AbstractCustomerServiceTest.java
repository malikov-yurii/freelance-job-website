package com.malikov.freelance.service;

public abstract class AbstractCustomerServiceTest extends AbstractServiceTest {

//    @Autowired
//    protected ClientService service;
//
//    @Test
//    public void testSave() throws Exception {
//        com.malikov.freelance.model.Client newCustomer = new com.malikov.freelance.model.Client(
//                "NewName", "NewLastName", "0671234567", "newCity", "1", "new@gmail.com", "");
//        com.malikov.freelance.model.Client created = service.save(newCustomer);
//        newCustomer.setId(created.getId());
//        USER_MATCHER.assertCollectionEquals(
//                Arrays.asList(CUSTOMER_DROGOV, CUSTOMER_DUNOV,
//                        CUSTOMER_GOLOV, newCustomer, CUSTOMER_WITHOUT_ANY_ORDER), service.getAll());
//    }
//
//    @Test
//    public void testUpdate() throws Exception {
//        com.malikov.freelance.model.Client updated = new com.malikov.freelance.model.Client(CUSTOMER_GOLOV);
//        updated.setName("Golov_Updated");
//        service.update(updated);
//        USER_MATCHER.assertEquals(updated, service.get(CUSTOMER_GOLOV.getId()));
//    }
//
//    @Test
//    public void testGet() throws Exception {
//        com.malikov.freelance.model.Client customer = service.get(CUSTOMER_DUNOV.getId());
//        USER_MATCHER.assertEquals(CUSTOMER_DUNOV, customer);
//    }
//
//    @Test
//    public void testGetByName() throws Exception {
//        Collection<com.malikov.freelance.model.Client> allByName = service.getByName(CUSTOMER_DROGOV.getName());
//        USER_MATCHER.assertCollectionEquals(Collections.singletonList(CUSTOMER_DROGOV), allByName);
//    }
//
//    @Test
//    public void testGetByLastName() throws Exception {
//        Collection<com.malikov.freelance.model.Client> allByLastName = service.getByLastName(CUSTOMER_GOLOV.getLastName());
//        USER_MATCHER.assertCollectionEquals(Collections.singletonList(CUSTOMER_GOLOV), allByLastName);
//    }
//
//    @Test
//    public void testGetByCity() throws Exception {
//        Collection<com.malikov.freelance.model.Client> allByCity = service.getByCity(CUSTOMER_DROGOV.getCity());
//        USER_MATCHER.assertCollectionEquals(Collections.singletonList(CUSTOMER_DROGOV), allByCity);
//    }
//
//    @Test
//    public void testGetByEmail() throws Exception {
//        com.malikov.freelance.model.Client customer = service.getByEmail(CUSTOMER_DROGOV.getEmail());
//        USER_MATCHER.assertEquals(CUSTOMER_DROGOV, customer);
//    }
//
//    @Test
//    public void testGetByPhoneNumber() throws Exception {
//        com.malikov.freelance.model.Client customer = service.getByPhoneNumber(CUSTOMER_DUNOV.getPhoneNumber());
//        USER_MATCHER.assertEquals(CUSTOMER_DUNOV, customer);
//    }
//
//    @Test
//    public void testGetAll() throws Exception {
//        Collection<com.malikov.freelance.model.Client> all = service.getAll();
//        USER_MATCHER.assertCollectionEquals(Arrays.asList(CUSTOMER_DROGOV, CUSTOMER_DUNOV,
//                CUSTOMER_GOLOV, CUSTOMER_WITHOUT_ANY_ORDER), all);
//    }
//
//    @Test
//    public void testDelete() throws Exception {
//        service.delete(CUSTOMER_WITHOUT_ANY_ORDER.getId());
//        USER_MATCHER.assertCollectionEquals(Arrays.asList(CUSTOMER_DROGOV, CUSTOMER_DUNOV, CUSTOMER_GOLOV), service.getAll());
//    }
}
