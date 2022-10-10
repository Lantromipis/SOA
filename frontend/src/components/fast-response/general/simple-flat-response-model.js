import {Button, Descriptions, Modal, Typography} from "antd";

const {Title} = Typography;

export function SimpleFlatResponseModel({title, value, visible, handleOk}) {

    const getDescription = (label, value) => {
        return(
            value
                ? <Descriptions.Item label={label}>{value}</Descriptions.Item>
                : <></>
        )
    }

    return (
        <>
            <Modal
                width={1200}
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
                    ? <Descriptions title="Flat info">
                        {getDescription("ID", value?.id)}
                        {getDescription("Price", value?.price)}
                        {getDescription("Area", value?.area)}
                        {getDescription("Number of rooms", value?.numberOfRooms)}
                        {getDescription("Transport", value?.transport)}
                        {getDescription("Height", value?.height)}
                        {getDescription("Name", value?.name)}
                        {getDescription("Coordinates x", value?.coordinates?.x)}
                        {getDescription("Coordinates y", value?.coordinates?.y)}
                        {getDescription("House name", value?.house?.name)}
                        {getDescription("House year", value?.house?.year)}
                        {getDescription("House number of floors", value?.house?.numberOfFloors)}
                        {getDescription("House number of lifts", value?.house?.numberOfLifts)}
                    </Descriptions>
                    : <Title>No response</Title>
                }

            </Modal>
        </>
    )
}