import {Divider, Layout, Space} from "antd";
import CustomHeader from "../components/general/custom-header";
import CustomFooter from "../components/general/custom-footer";
import {Content} from "antd/es/layout/layout";
import {GetTotalCostModal} from "../components/fast-response/get-total-cost-modal";
import {GetCheapestModal} from "../components/fast-response/get-cheapest-modal";
import {SponsorHouseForm} from "../components/forms/sponsor-form";
import {CountriesTable} from "../components/tables/countries-table";
import {HousesTable} from "../components/tables/houses-table";

export function SecretPage() {
    return(
        <>
            <Layout style={{minHeight: "100vh"}}>
                <CustomHeader
                    selectedMenuItem={'secret'}
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
                        <SponsorHouseForm/>
                        <Divider style={{minWidth:600}}/>
                        <CountriesTable/>
                        <Divider style={{minWidth:600}}/>
                        <HousesTable/>
                    </Space>
                </Content>
                <CustomFooter/>
            </Layout>
        </>
    )
}