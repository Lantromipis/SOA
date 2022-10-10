import FlatsTable from "../components/tables/flats-table";
import {Button, Layout, Modal} from "antd";
import CustomFooter from "../components/general/custom-footer";
import CustomHeader from "../components/general/custom-header";
import {useState} from "react";
import FlatCreateForm from "../components/forms/flat-create-form";

const {Content} = Layout;

export default function FlatsCatalogPage() {
    const [isCreateFlatModalOpen, setIsCreateFlatModalOpen] = useState(false);

    const showCreateFlatModal = () => {
        setIsCreateFlatModalOpen(true);
    };

    const handleCreateFlatOk = () => {
        setIsCreateFlatModalOpen(false);
    };

    const handleCreateFlatCancel = () => {
        setIsCreateFlatModalOpen(false);
    };

    return (
        <>
            <Layout>
                <CustomHeader
                    selectedMenuItem={'catalog'}
                />
                <Content>
                    <FlatsTable
                        pageSize={5}
                    />
                    <Button type="primary" onClick={showCreateFlatModal}>
                        Add new flat
                    </Button>
                    <FlatCreateForm
                        formVisible={isCreateFlatModalOpen}
                        onCancel={handleCreateFlatCancel}
                        onFinish={handleCreateFlatOk}
                    />
                </Content>
                <CustomFooter/>
            </Layout>
        </>
    )
}