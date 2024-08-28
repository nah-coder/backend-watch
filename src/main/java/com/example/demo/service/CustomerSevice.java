package com.example.demo.service;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Roles;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerSevice implements CustomerServiceSrc{
    @Autowired
    private CustomerRepository customerRepository;

    public Customer findCustomerById(int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            System.out.println("Customer not found");
        }
        return customer.get();
    }

    public Page<Customer> findAllpage(Integer pageno){
        Pageable pageable = PageRequest.of(pageno-1,4);
        return  customerRepository.findAll(pageable);
    }

    public List<Customer> searchCustomer(String keyword){
        return  customerRepository.searchCustomerBy(keyword);
    }

    public Page<Customer> searchCustomer(String keyword,Integer pageno){
        List list = this.searchCustomer(keyword);
        Pageable pageable = PageRequest.of(pageno-1,2);
        Integer start = (int) pageable.getOffset();
        Integer end = (int)(pageable.getOffset() + pageable.getPageSize() > list.size() ? list.size() : pageable.getOffset() + pageable.getPageSize());
        list = list.subList(start, end);
        return new PageImpl<Customer>(list,pageable,this.searchCustomer(keyword).size());
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public String saveCustomer(CustomerDTO customerDTO) {
        Customer savedCustomer = new Customer();
        savedCustomer.setName(customerDTO.getName());
        savedCustomer.setUsername(customerDTO.getUsername());
        savedCustomer.setPassword(customerDTO.getPassword());
        savedCustomer.setRoles(customerDTO.getRole());
        savedCustomer.setEmail(customerDTO.getEmail());
        savedCustomer.setAddress(customerDTO.getAddress());
        savedCustomer.setCreatedDate(customerDTO.getCreatedDate());
        savedCustomer.setPhone(customerDTO.getPhone());
        savedCustomer.setIsactive(customerDTO.getIsactive());
        customerRepository.save(savedCustomer);
        return "Customer saved";
    }

    public String updateCustomer(int id,CustomerDTO customerDTO) {
        boolean exists = customerRepository.existsById(customerDTO.getId());
        if (!exists) return "Customer not found";
        Customer updateCustomer = new Customer();
        updateCustomer.setId(id);
        updateCustomer.setName(customerDTO.getName());
        updateCustomer.setUsername(customerDTO.getUsername());
        updateCustomer.setPassword(customerDTO.getPassword());
//        updateCustomer.setRole(customerDTO.getRole());
        updateCustomer.setEmail(customerDTO.getEmail());
        updateCustomer.setAddress(customerDTO.getAddress());
        updateCustomer.setCreatedDate(customerDTO.getCreatedDate());
        updateCustomer.setPhone(customerDTO.getPhone());
        updateCustomer.setIsactive(customerDTO.getIsactive());
        customerRepository.save(updateCustomer);
        return "Customer updated";
    }

    public String deleteCustomer(int id) {
        boolean exists = customerRepository.existsById(id);
        if (!exists) return "Customer not found";
        customerRepository.deleteById(id);
        return "Customer deleted";
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);
        if(customer==null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(customer.getUsername(), customer.getPassword(),rolesToAuthorities(customer.getRoles()));
    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Roles> roles){
        return roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


}
