package jp.co.sss.crud.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jp.co.sss.crud.filter.Crudfilter;
import jp.co.sss.crud.filter.LoginFilter;

@Configuration
public class FilterConfig implements WebMvcConfigurer {
	
	@Bean
	public FilterRegistrationBean<LoginFilter> configLoginFilter() {
		FilterRegistrationBean<LoginFilter> bean = new FilterRegistrationBean<LoginFilter>();
		
		bean.setFilter(new LoginFilter());
		bean.setOrder(1);
		return bean;
	}
	
	@Bean
	public FilterRegistrationBean<Crudfilter> configCrudFilter() {
		FilterRegistrationBean<Crudfilter> bean = new FilterRegistrationBean<Crudfilter>();
		
		bean.setFilter(new Crudfilter());
		bean.addUrlPatterns("/regist/*");
		bean.addUrlPatterns("/delete/*");
		bean.setOrder(2);
		return bean;
	}
}
