package com.mywebapp.web.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class ProductService {

    @Autowired private ProductRepository repo;

    public List<Product> listAll()
    {
        return (List<Product>) repo.findAll();
    }
    public void save(Product user)
    {

        repo.save(user);
    }
    public Product get(Integer id) throws UserNotFoundException {
        Optional<Product> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID " + id);
    }
    public void delete(Integer id) throws UserNotFoundException {
        Integer count = repo.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not find any Product with ID " + id);
        }
        repo.deleteById(id);
    }
}
