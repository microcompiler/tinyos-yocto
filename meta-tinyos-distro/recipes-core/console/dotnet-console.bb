DESCRIPTION = "Microsoft .NET Console Applicaiton"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit dotnet

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

DOTNET_PROJECT = "Console.csproj"

SRC_URI += "\
    file://Console.sln \
    file://Console.csproj \
    file://Program.cs \
"