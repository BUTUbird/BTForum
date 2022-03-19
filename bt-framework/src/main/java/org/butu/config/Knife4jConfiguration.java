package org.butu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: BTForum
 * @description: Knife4j配置类
 * @packagename: org.butu.config
 * @author: BUTUbird
 * @date: 2022-03-19 23:18
 **/


@Configuration
@EnableSwagger2
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {

        //添加head参数start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("token").description("令牌").defaultValue("设置token默认值").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        //添加head参数end

        ApiSelectorBuilder builder = new Docket(DocumentationType.SWAGGER_2)
                .enableUrlTemplating(false)
                .apiInfo(apiInfo())
                //选择那些路径和api会生成document
                .select()
                //对所有api进行监控
                .apis(RequestHandlerSelectors.any())
                //自定义过滤
                .paths(this::filterPath);
        return builder.build();
    }
    private boolean filterPath(String path){
        boolean ret = path.endsWith("/error");
        if (ret){
            return false;
        }
        //其它过滤逻辑
        return true;
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Forum")
                .description("基于SpringBoot和Vue实现的论坛")
                .termsOfServiceUrl("https://github.com/BUTUbird")
                .version("1.0")
                .contact(new Contact("BUTUbird", "https://www.cnblogs.com/BUTU", "1781894948@qq.com"))
                .build();
    }
}
/**
 * var code=ke.response.data.code;
 * if(code==200){
 *     //判断,如果服务端响应code是8200才执行操作
 *     //获取token
 *     var token=ke.response.data.data.token;
 *     //1、如何参数是Header，则设置当前逻辑分组下的全局Header
 *     ke.global.setHeader("Authorization",'Bearer '+token);
 *
 * }
 */

