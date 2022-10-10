import {Divider, Layout, Space} from "antd";
import CustomHeader from "../components/general/custom-header";
import CustomFooter from "../components/general/custom-footer";
import {Content} from "antd/es/layout/layout";
import {GetTotalCostModal} from "../components/fast-response/get-total-cost-modal";
import {GetCheapestModal} from "../components/fast-response/get-cheapest-modal";

export function AgencyPage() {
    return(
        <>
            <Layout style={{minHeight: "100vh"}}>
                <CustomHeader
                    selectedMenuItem={'agency'}
                />
                <Content>
                    <Space style={{
                        marginTop: 100,
                        display: "flex",
                        alignItems: "center",
                        justifyContent: "center",
                        flexDirection: "column",
                    }}
                           size={0}
                    >
                        <GetTotalCostModal/>
                        <Divider style={{minWidth:600}}/>
                        <GetCheapestModal/>
                    </Space>
                </Content>
                <CustomFooter/>
            </Layout>
        </>
    )
}