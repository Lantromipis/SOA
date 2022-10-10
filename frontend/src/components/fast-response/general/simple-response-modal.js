import {Button, Modal, Typography} from "antd";

const {Title} = Typography;

export function SimpleResponseModel({title, value, visible, handleOk}) {

    return (
        <>
            <Modal
                open={visible}
                title={title}
                onCancel={handleOk}
                footer={[
                    <Button key="submit" type="primary" onClick={handleOk}>
                        Ok
                    </Button>,
                ]}
            >

                {value
                    ? <Title>Response: {value}</Title>
                    : <Title>No response</Title>
                }

            </Modal>
        </>
    )
}