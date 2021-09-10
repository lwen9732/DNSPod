package cn.codepai;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.dnspod.v20210323.DnspodClient;
import com.tencentcloudapi.dnspod.v20210323.models.*;

/**
 * 域名解析记录更新
 */
public class DomainUpdater {
    public static final String domain = "codepai.cn";
    public static final String secretId = "AKID77vVs1wd6LNBl7GG4g5GDvPm4IQUSL7p";
    public static final String secretKey = "fGnEbs5CD90mT6d4Qm3GPRMBwb8buzrW";
    public static final Long recordId = 507469849L;
    public static final String endpoint = "dnspod.tencentcloudapi.com";

    public static String ip = "127.0.0.1";

    // 修改域名记录
    public static void updateRecordInfo(String ipAddr){
        if (null != ipAddr && !ipAddr.equals(ip)){
            try{
                // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
                // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
                Credential cred = new Credential(secretId, secretKey);
                // 实例化一个http选项，可选的，没有特殊需求可以跳过
                HttpProfile httpProfile = new HttpProfile();
                httpProfile.setEndpoint(endpoint);
                // 实例化一个client选项，可选的，没有特殊需求可以跳过
                ClientProfile clientProfile = new ClientProfile();
                clientProfile.setHttpProfile(httpProfile);
                // 实例化要请求产品的client对象,clientProfile是可选的
                DnspodClient client = new DnspodClient(cred, "", clientProfile);
                // 实例化一个请求对象,每个接口都会对应一个request对象
                ModifyRecordRequest req = new ModifyRecordRequest();

                req.setDomain(domain);
                req.setRecordId(recordId);
                req.setValue(ipAddr);
                req.setRecordLine("默认");
                req.setRecordType("A");

                // 返回的resp是一个ModifyRecordResponse的实例，与请求对象对应
                ModifyRecordResponse resp = client.ModifyRecord(req);
                // 输出json格式的字符串回包
                System.out.println(ModifyRecordResponse.toJsonString(resp));
                System.out.println("原始ip["+ip+"],变更为["+ipAddr+"]!");
            } catch (TencentCloudSDKException e) {
                System.out.println(e.toString());
                System.out.println("ip更新失败！");
            }
        }

    }

}
