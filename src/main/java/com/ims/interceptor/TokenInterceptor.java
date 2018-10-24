package com.ims.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.ims.entity.Login;
import com.ims.util.JWT;
import com.ims.util.ResponseData;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class TokenInterceptor implements HandlerInterceptor {
    //拦截每个请求
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        String token = request.getParameter("token");
        ResponseData responseData = ResponseData.ok();
        //token不存在
        if(null != token){
            Login login = JWT.unsign(token, Login.class);
            String uid = request.getParameter("userid");
            if(null != uid && null !=login){
                if(Integer.parseInt(uid)==login.getUid()){
                    return true;
                }else{
                    responseData = ResponseData.forbidden();
                    responseMessage(response, response.getWriter(), responseData);
                    return false;
                }

            }else{
                responseData = ResponseData.forbidden();
                responseMessage(response, response.getWriter(), responseData);
                return false;
            }
        }else{
            responseData = ResponseData.forbidden();
            responseMessage(response, response.getWriter(), responseData);
            return false;

        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    //请求不通过，返回错误信息给客户端
    private void responseMessage(HttpServletResponse response, PrintWriter out, ResponseData responseData){
        responseData = ResponseData.forbidden();
        response.setContentType("application/json; charset=utf-8");
        String json = JSONObject.toJSONString(responseData);

        out.print(json);
        out.flush();
        out.close();
    }
}
