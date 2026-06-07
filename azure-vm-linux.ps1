$RESOURCE_GROUP="rg-astrocolony-rm565824"
$LOCATION="eastus2"

$VNET_NAME="vnet-astrocolony-rm565824"
$SUBNET_NAME="subnet-astrocolony-rm565824"
$NSG_NAME="nsg-astrocolony-rm565824"
$PUBLIC_IP_NAME="ip-astrocolony-rm565824"
$NIC_NAME="nic-astrocolony-rm565824"

$VM_NAME="vm-astrocolony-rm565824"
$ADMIN_USER="azureuser"

# VM SIZE
$SIZE="Standard_D2s_v3"

# IMAGEM QUE FUNCIONA EM CONTA ACADÊMICA
$IMAGE="Ubuntu2204"

Write-Host "Criando Resource Group..."

az group create `
  --name $RESOURCE_GROUP `
  --location $LOCATION

Write-Host "Criando VNET..."

az network vnet create `
  --resource-group $RESOURCE_GROUP `
  --name $VNET_NAME `
  --subnet-name $SUBNET_NAME `
  --location $LOCATION

Write-Host "Criando NSG..."

az network nsg create `
  --resource-group $RESOURCE_GROUP `
  --name $NSG_NAME `
  --location $LOCATION

Write-Host "Liberando SSH..."

az network nsg rule create `
  --resource-group $RESOURCE_GROUP `
  --nsg-name $NSG_NAME `
  --name allow-ssh `
  --priority 1000 `
  --destination-port-ranges 22 `
  --access Allow `
  --protocol Tcp

Write-Host "Liberando porta 8080..."

az network nsg rule create `
  --resource-group $RESOURCE_GROUP `
  --nsg-name $NSG_NAME `
  --name allow-8080 `
  --priority 1010 `
  --destination-port-ranges 8080 `
  --access Allow `
  --protocol Tcp

Write-Host "Criando IP público..."

az network public-ip create `
  --resource-group $RESOURCE_GROUP `
  --name $PUBLIC_IP_NAME `
  --location $LOCATION `
  --sku Standard

Write-Host "Criando NIC..."

az network nic create `
  --resource-group $RESOURCE_GROUP `
  --name $NIC_NAME `
  --vnet-name $VNET_NAME `
  --subnet $SUBNET_NAME `
  --network-security-group $NSG_NAME `
  --public-ip-address $PUBLIC_IP_NAME `
  --location $LOCATION

Write-Host "Criando VM..."

az vm create `
  --resource-group $RESOURCE_GROUP `
  --name $VM_NAME `
  --nics $NIC_NAME `
  --image $IMAGE `
  --size $SIZE `
  --admin-username $ADMIN_USER `
  --generate-ssh-keys `
  --location $LOCATION

Write-Host "IP da VM:"

az network public-ip show `
  --resource-group $RESOURCE_GROUP `
  --name $PUBLIC_IP_NAME `
  --query ipAddress `
  --output tsv