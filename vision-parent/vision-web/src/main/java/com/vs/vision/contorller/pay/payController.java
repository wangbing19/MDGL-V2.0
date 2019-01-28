package com.vs.vision.contorller.pay;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/pay/")
public class payController {
    /**
     * 商户账号：p1_MerId=10001126856
     * 商户的keyVaue：keyValue=69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl
     * p1_MerId=10000326625
     * <p>
     * keyValue=0acqgug6x57m0wrsiod6clpn1ezh47r2ot5h1zkq5dztiic8y5xkm5g0p0ek
     */
    @RequestMapping(value = "dopay",method = RequestMethod.POST)
    public String payServlet(@RequestParam Long p2Order, @RequestParam Double p3Amt, @RequestParam String pd_FrpId) throws IOException {
        System.out.println("payController.payServlet");
        ReqAndResp reqAndResp = new ReqAndResp().invoke();
        HttpServletRequest request = reqAndResp.getRequest();
        HttpServletResponse response = reqAndResp.getResponse();
        System.out.println("p2Order = [" + p2Order + "], p3Amt = [" + p3Amt + "], pdFrpId = [" + pd_FrpId + "]");
        String p0_Cmd = "Buy",							//p0_Cmd 业务类型
                p1_MerId = "10001126856",				//p1_MerId 商户编号
                p2_Order = Long.toString(p2Order),		//p2_Order 商户订单号
                p3_Amt = Double.toString(p3Amt),		//p3_Amt 支付金额
                p4_Cur = "CNY",							//p4_Cur 交易币种
                p5_Pid = "",							//p5_Pid 商品名称
                p6_Pcat = "",							//p6_Pcat 商品种类
                p7_Pdesc = "",							//p7_Pdesc 商品描述
                p8_Url = "http://localhost:8020/pay/getBack",//p8_Url 商户接收支付成功数据的地址
                p9_SAF = "",							//p9_SAF 送货地址
                pa_MP = "",								//pa_MP 商户扩展信息
                pF = pd_FrpId,							//pF 银行编码
                pr_NeedResponse = "1";					//pr_NeedResponse 应答机制
        String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";//keyValue 商户密钥
        String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pF, pr_NeedResponse, keyValue);
        String url = "https://www.yeepay.com/app-merchant-proxy/node?" +
                "&p0_Cmd=" + p0_Cmd +
                "&p1_MerId=" + p1_MerId +
                "&p2_Order=" + p2_Order +
                "&p3_Amt=" + p3_Amt +
                "&p4_Cur=" + p4_Cur +
                "&p5_Pid=" + p5_Pid +
                "&p6_Pcat=" + p6_Pcat +
                "&p7_Pdesc=" + p7_Pdesc +
                "&p8_Url=" + p8_Url +
                "&p9_SAF=" + p9_SAF +
                "&pa_MP=" + pa_MP +
                "&pd_FrpId=" + pF +
                "&pr_NeedResponse=" + pr_NeedResponse +
                "&hmac=" + hmac;

        System.out.println("url = " + url);
        return "redirect:" + url;
    }

    @RequestMapping(value = "getBack")
    public String payCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	System.out.println("11111111111111111111");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String r1_Code = request.getParameter("r1_Code");
        PrintWriter pw = response.getWriter();
        if ("1".equals(r1_Code)) {
            String p1_MerId = request.getParameter("p1_MerId");
            String r3_Amt = request.getParameter("r3_Amt");
            String r6_Order = request.getParameter("r6_Order");
            String rp_PayDate = request.getParameter("rp_PayDate");
            pw.println("支付成功<br/>"
                    + "商户编号：" + p1_MerId + "<br/>"
                    + "支付金额：" + r3_Amt + "<br/>"
                    + "商户订单号：" + r6_Order + "<br/>"
                    + "支付成功时间：" + rp_PayDate+"<br/>"
                    +"3秒后返回主页");
            response.setHeader("refresh", "3;url=http://localhost:8020/doIndexUI.do");
        } else {
            pw.println("支付失败");
        }
        return null;
    }

    private class ReqAndResp {
        private HttpServletRequest request;
        private HttpServletResponse response;

        public HttpServletRequest getRequest() {
            return request;
        }

        public HttpServletResponse getResponse() {
            return response;
        }

        public ReqAndResp invoke() throws UnsupportedEncodingException {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            request = ((ServletRequestAttributes) requestAttributes).getRequest();
            response = ((ServletRequestAttributes) requestAttributes).getResponse();
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            return this;
        }
    }
}
