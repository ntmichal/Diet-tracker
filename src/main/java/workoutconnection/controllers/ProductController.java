package workoutconnection.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import workoutconnection.dto.ProductDto;
import workoutconnection.entities.Product;
import workoutconnection.service.IProductService;

import javax.persistence.NoResultException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {


    private IProductService productService;
    @Autowired
    public ProductController(IProductService productService){
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        ProductDto productDto;
        try{
            productDto = productService.getProduct(id);

        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDto);
    }

    @GetMapping()
    public ResponseEntity getProduct(@RequestParam("name") String name){
        List<ProductDto> foundProducts = productService.findProductByName(name);
        if(foundProducts.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(foundProducts);
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<ProductDto> productList(){
        return productService.getAllProducts();
    }



    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductDto product){
        ProductDto savedProduct = productService.insertProduct(product);

        //response path
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedProduct.getId()).toUri();

        return ResponseEntity.created(location).build();

    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        try{
            productService.getProduct(id);
            productService.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }


    //TODO zabezpieczyć odpowiednio
    @RequestMapping(value = "/api/product/{id}", method = RequestMethod.PUT)
    public void updateProduct(@RequestBody ProductDto product, @PathVariable Long id){
        product.setId(id);
        productService.update(product);
    }
}
