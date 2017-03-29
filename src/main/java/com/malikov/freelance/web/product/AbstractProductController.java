package com.malikov.freelance.web.product;

public abstract class AbstractProductController {
//    private static final Logger LOG = LoggerFactory.getLogger(AbstractProductController.class);
//
//    @Autowired
//    private ProductService productService;
//
//    public Product get(int id) {
//        LOG.info("get product {}", id);
//        return productService.get(id);
//    }
//
//    public void delete(int id) {
//        LOG.info("delete product {}", id);
//        productService.delete(id);
//    }
//
//    public List<ProductTo> getAll() {
//        LOG.info("getAll products");
//        List<ProductTo> allProductTos = new ArrayList<>();
//        for (Product product : productService.getAll()) {
//            allProductTos.addAll(ProductUtil.getToListFrom(product));
//        }
//        return allProductTos;
//    }
//
//    public void update(Product product, int id) {
//        product.setId(id);
//        LOG.info("update product{}", product);
//        productService.update(product);
//    }
//
//    public Product create(Product product) {
//        product.setId(null);
//        LOG.info("create product{}", product);
//        return productService.save(product);
//    }
//
//    public List<ProductTo> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
//        LOG.info("getBetween dates {} - {} for time {} - {} ", startDate, endDate, startTime, endTime);
//
//        // TODO: 1/16/2017 I NEED IMPLEMENT THIS METHOD
//        return getAll();
//    }
//
//    public void enableUnlimited(int id, boolean unlimited) {
//        productService.enableUnlimited(id, unlimited);
//    }
//
//    public void enableHasVariations(int id, boolean hasVariations) {
//        productService.enableHasVariations(id, hasVariations);
//    }
}