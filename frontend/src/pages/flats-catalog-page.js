import FlatsTable from "../components/tables/flats-table";
import {Divider, Layout, Space} from "antd";
import CustomFooter from "../components/general/custom-footer";
import CustomHeader from "../components/general/custom-header";
import {DeleteFlatForm} from "../components/forms/delete-flat-form";
import {ModifyFlatForm} from "../components/forms/modify-flat-form";
import {AddFlatForm} from "../components/forms/add-flat-form";
import {HeightSumModal} from "../components/fast-response/height-sum-modal";
import {CountByNewModal} from "../components/fast-response/count-by-new-modal";
import {GetWithMaxIdModal} from "../components/fast-response/get-with-max-id-modal";

const {Content} = Layout;

export default function FlatsCatalogPage() {
    return (
        <>
            <Layout style={{minHeight: "100vh"}}>
                <CustomHeader
                    selectedMenuItem={'catalog'}
                />
                <Content>
                    <FlatsTable
                        pageSize={5}
                    />
                    <Divider/>
                    <Space style={{
                        display: "flex",
                        alignItems: "center",
                        justifyContent: "center",
                        flexDirection: "column",
                    }}
                           size={0}
                    >
                        <AddFlatForm/>
                        <Divider style={{minWidth:600}}/>
                        <ModifyFlatForm/>
                        <Divider style={{minWidth:600}}/>
                        <DeleteFlatForm/>
                        <Divider style={{minWidth:600}}/>
                        <HeightSumModal/>
                        <Divider style={{minWidth:600}}/>
                        <CountByNewModal/>
                        <Divider style={{minWidth:600}}/>
                        <GetWithMaxIdModal/>
                    </Space>

                </Content>
                <CustomFooter/>
            </Layout>
        </>
    )
}